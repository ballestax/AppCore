/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;


import com.core.domain.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.log4j.Logger;
import org.balx.Imagenes;

/**
 *
 * @author hp
 */
public final class Aplication implements ActionListener, PropertyChangeListener, ListSelectionListener {

    public static final String TITLE = "App Core";
    public static final String VERSION = "1.0"; 
    public static final String ACTION_CANCEL_PANEL = "acCancelPanel";
    public static final String ACTION_EXIT_APP = "acExitApp";
    public static final String ACTION_SHOW_PREFERENCES = "acShowPreferences";
    public static final String ACTION_SHOW_ADMIN = "acShowAdmin";
    public static final String ACTION_RETURN_TO_MENU = "acReturnToMenu";
    public static final String ACTION_CLOSE_SESION = "acCLoseSesion";
    public static final String CONFIG_LASTUPDATE = "lastUpdate";

    
    public static final String PREFERENCES = "";
    public static final String DATABASE = "";
    public static final String WORK_FOLDER = "wfolder";
    protected static final boolean INSTALL_DB = false;    
    private static final boolean messaged = !true;    
    public static final String DEFAULT_EXPORT_DIR = "";

    //Correr la aplicacion con configuracion de servidor local
    private static boolean local = true;
    public static final int LIMIT = 30000;

    private boolean tserver;
    private final Configuration configuration;
    private ImageManager imgManager;
    private GUIManager guiManager;
    private SQLManager sqlManager;
    private XLSManager xlsManager;
    private ProgAction acExitApp, acShowPreferences, acReturnToMenu,
            acShowAdmin, acCerrarSesion;
    private final Control control;
    private final ControlFilters ctrlFilters;
    private final BackupsCtrl ctrlBackup;
    private final Image imageBC;
    public static final int WC = 80;
    private final SimpleDateFormat formatoFecha;
    private User user;
    private static final Logger logger = Logger.getLogger(Aplication.class.getCanonicalName());
    private SwingWorker sw;
    private ScheduledExecutorService ses;
    private SimpleDateFormat sdfExport;
    private PropertyChangeSupport pcs;
    private String folderIcons = "gui/img/";

    public Aplication() {

        //log4j 
        Properties properties = new Properties();
        try {
            String logFile = Aplication.getDirTrabajo() + File.separator + "logging.log";
            properties.load(new FileInputStream("config/logging.properties"));
            properties.put("log4j.appender.file.File", logFile);
            org.apache.log4j.PropertyConfigurator.configure(properties);
        } catch (IOException ex) {
            System.err.println("No se encuentra el archivo de configuracion");
        }

        logger.debug("Iniciando la aplicacion..");

        pcs = new PropertyChangeSupport(this);

        tserver = false;
        configuration = Configuration.getInstancia();
        imgManager = ImageManager.getInstance();
        sqlManager = SQLManager.getInstance(this);
        guiManager = GUIManager.getInstance(this);
        xlsManager = XLSManager.getInstance(this);

        configWorkFolder();
        Utiles.crearDirectorio(Paths.get(getDirPics(), ""));
        configuration.setPath(getDirTrabajo());
        configuration.load();
        

        String path = Aplication.getDirTrabajo() + File.separator;

                
        configuration.setProperty(Configuration.OCR_DIR_WK, path);
        configuration.setProperty(Configuration.OCR_IMAGE_INPUT, "img.jpg");
        configuration.setProperty(Configuration.OCR_TEXT_OUTPUT, "out");

        
        
        
        String prop = configuration.getProperty(Configuration.DINST, "NULL");
        if ("NULL".equals(prop)) {
            configuration.setProperty(configuration.DINST, new Date().toString());
        }
        int cus = configuration.getProperty(Configuration.CUS, 0);
        configuration.setProperty(configuration.CUS, String.valueOf(++cus));


        initActions();
        formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        sdfExport = new SimpleDateFormat("yyyyMMddHHmmss");

        control = new Control(this);
        String propST = configuration.getProperty(Configuration.DATABASE_STATION, "false");
        if (!"true".equals(propST)) {
            control.initDatabase();
        }
              
        configuration.save();


        ctrlFilters = new ControlFilters();
        ctrlBackup = new BackupsCtrl(this);

        getImgManager().getImagen(getFolderIcons() + "person_hl.png", 100, 100);
        imageBC = createImage();

//        setupDataBase();
//        comprobarDataBase();
//        control.getVotingPlaceFromDivipol("Colegio sta catalina de siena", "la guajira", "maicao");
    }

    public String getFolderIcons() {
        return folderIcons;
    }

    public static boolean isLocal() {
        return local;
    }


    public void init() {
//        checkTime();
        verifyLicTime();
        configDatabase();
        getGuiManager().configurar();

        try {
//            JDBCDAOFactory.getInstance().clean();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean getTimeServer() {
        boolean verify = false;
        try {
            Install it = new Install();
            Date tServer = new TimeWaste().getTimeServer();
            if (tServer == null || tServer.after(it.getTWST())) {
                verify = false;
            } else {
                verify = true;
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            System.err.println(e.getMessage());
        }
        return verify;
    }

    public synchronized void checkTime() {
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                logger.debug("Cheking server time");
                boolean tser = getTimeServer();
                tserver = tser;
                while (!tser) {
                    tser = getTimeServer();
                    tserver = tser;
                }
                logger.debug("Time server verified");
            }
        });
        hilo.start();
    }

    public void verifyLicTime() {

        Runnable runTask = new Runnable() {

            @Override
            public void run() {
                tserver = getTimeServer();
            }
        };

        ses = Executors.newScheduledThreadPool(2);
        ses.scheduleAtFixedRate(runTask, 1, 15, TimeUnit.SECONDS);

    }

    public void stopTask() {
        if (ses != null) {
            ses.shutdown();
        }
    }

    public boolean isTserver() {
        return tserver;
    }

    public void workOffline() {
        if (new Date().before(new Install().getTWST())) {
            this.tserver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case Aplication.ACTION_CANCEL_PANEL:
                ((JButton) evt.getSource()).getRootPane().getParent().setVisible(false);
                break;
        }
    }

    public User getUser() {
        return user;
    }

    private Color getColor() {
        return org.balx.Utiles.colorAleatorio(20, 150);
    }


    public void setUser(User user) {
        user.setPassword("");
        this.user = user;
        getGuiManager().changeUser();
    }

    public SimpleDateFormat getSdfExport() {
        return sdfExport;
    }

    public final boolean isLimit() {
        return true;
    }

    public static boolean isMessaged() {
        return messaged;
    }

    public final int getLimit() {
        return LIMIT;
    }

    protected final String getMap() {
        StringBuilder map = new StringBuilder();
        map.append(Aplication.TITLE).append("\n");        
        
        map.append("di:").append(getConfiguration().getProperty(configuration.DINST)).append("\n");
        map.append("os:").append(System.getProperty("os.name")).append("\n");
        map.append("usm:").append(System.getProperty("user.name")).append("\n");
        map.append("usl:").append(getUser()).append("\n");
        return map.toString();
    }

    public void configDatabase() {
        logger.debug("setting the database");
        String driver = configuration.getProperty(Configuration.DATABASE_DRIVER);
        String prefijo = configuration.getProperty(Configuration.DATABASE_PREFIJO);
        String user = configuration.getProperty(Configuration.DATABASE_USER);
        String pass = configuration.getProperty(Configuration.DATABASE_PASSWORD);
        String url = configuration.getProperty(Configuration.DATABASE_URL);
        DBManager.getInstance().setupDatabase(driver, prefijo, url, user, pass);

    }

    private Image createImage() {
        Font f1 = new Font("Agency FB", 1, 16);
        Image centrarTexto = org.balx.Imagenes.centrarTexto(WC, WC, TITLE.toUpperCase(), f1, Color.white, Color.blue);
        return Imagenes.imagenToGray(centrarTexto, "");
    }

    public Image getImageBC() {
        return imageBC;
    }


    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }

    private void initActions() {
        acExitApp = new ProgAction("Salir",
                new ImageIcon(imgManager.getImagen(getFolderIcons() + "Salir.png", 32, 32)), "Salir de la aplicacion", 'x') {
            public void actionPerformed(ActionEvent e) {
                salir(0);
            }

        };
        acExitApp.setSmallIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "exit.png", 25, 25)));
        acExitApp.setLargeIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "exit.png", 32, 32)));

        acShowPreferences = new ProgAction("Preferencias",
                null, "Configurar preferencias del programa", 'P') {
            public void actionPerformed(ActionEvent e) {
                getGuiManager().showPreferences();
            }
        };
        acShowPreferences.setSmallIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "preferencias.png", 25, 25)));
        acShowPreferences.setLargeIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "preferencias.png", 32, 32)));
        acShowPreferences.setEnabled(false);

        
        acReturnToMenu = new ProgAction("Volver",
                null, "Volver al menu Principal", 'v') {
            public void actionPerformed(ActionEvent e) {
                getGuiManager().showMenuPrc();
            }
        };
        
        
        acCerrarSesion = new ProgAction("Cerrar secion",
                null, "Cerrar la sesion del usuario actual", 'x') {
            public void actionPerformed(ActionEvent e) {
                getGuiManager().closeSesion();
            }
        };
        acCerrarSesion.setSmallIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "system-log-out.png", 25, 25)));
        acCerrarSesion.setLargeIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "system-log-out.png", 32, 32)));

    
        acShowAdmin = new ProgAction("Administrar",
                null, "Ver modulo de administracion", 'a') {
            public void actionPerformed(ActionEvent e) {
                getGuiManager().showAdminModule();
            }
        };
        acShowAdmin.setSmallIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "admin.png", 25, 25)));
        acShowAdmin.setLargeIcon(new ImageIcon(imgManager.getImagen(getFolderIcons() + "admin.png", 32, 32)));
    
    }

    public AbstractAction getAction(String action) {
        switch (action) {
            case ACTION_SHOW_PREFERENCES:
                return acShowPreferences;
            case ACTION_EXIT_APP:
                return acExitApp;            
            case ACTION_RETURN_TO_MENU:
                return acReturnToMenu;
            case ACTION_CLOSE_SESION:
                return acCerrarSesion;
            case ACTION_SHOW_ADMIN:
                return acShowAdmin;                
            default:
                return null;
        }
    }

    public Control getControl() {
        return control;
    }

    public BackupsCtrl getCtrlBackup() {
        return ctrlBackup;
    }

    public ControlFilters getCtrlFilters() {
        return ctrlFilters;
    }


    public Configuration getConfiguration() {
        return configuration;
    }

    public ImageManager getImgManager() {
        return imgManager;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public XLSManager getXlsManager() {
        return xlsManager;
    }

    public String getUserHome() {
        return System.getProperty("user.home");
    }

    public String getRunDir() {
        return System.getProperty("user.dir");
    }

    public String getDirDocuments() {
        Path dir = Paths.get(System.getProperty("user.home"), "");
        return dir.toString() + File.separator + "Documents";
    }

    public static String getDirTrabajo() {
        String SUF = ".";
        if (System.getProperty("os.name").toUpperCase().contains("XP")) {
            SUF = "";
        }
        return System.getProperty("user.home") + File.separator + SUF + WORK_FOLDER;
    }

    public static String getDirPics() {
        return getDirTrabajo() + File.separator + "pics";
    }

    public void salir(int i) {
        logger.debug("Closing aplication..");
        guiManager.getFrame().setVisible(false);
        guiManager.getFrame().dispose();
        configuration.savePreferences();
        configuration.save();
        System.exit(i);
    }

    private boolean configWorkFolder() {
        logger.debug("Setting work folder");
        Path path;
        boolean creado = false;
        path = Paths.get(getDirTrabajo(), "");
        if (System.getProperty("os.name").toUpperCase().contains("XP")) {
            logger.debug("Setting for Windows XP system");
            path = Paths.get(getUserHome(), "");
        }
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(path);
                path = path.toAbsolutePath();
                logger.debug("\n" + path + " directorio creado.");
                return true;
            } catch (NoSuchFileException e) {
                creado = false;
                logger.debug("\nDirectory creation failed:\n" + e);
            } catch (FileAlreadyExistsException e) {
                creado = false;
                System.err.println("\nDirectory creation failed:\n" + e);
            } catch (IOException e) {
                creado = false;
                logger.debug("\nDirectory creation failed:\n" + e);
            }
        } else {
            logger.debug("Directory already exist");
        }
        return creado;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    protected void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    protected void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

}
