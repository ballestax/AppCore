/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcore;

import com.core.Aplication;
import com.core.GUIManager;

/**
 *
 * @author ballestas
 */
public class AppCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Aplication app = new Aplication();
                    app.init();
                                        
                } catch (Exception ex) {
                    GUIManager.showErrorMessage(null, ex, "Error al iniciar la aplicacion");
                }

            }
        });
        
    }
    
}
