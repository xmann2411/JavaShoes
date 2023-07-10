/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.view.LoginDialog;
import hr.algebra.view.MainFrame;
import java.awt.EventQueue;
import java.util.Optional;

/**
 *
 * @author Karla
 */
public class Main {
    public static void main(String args[]){
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            final MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            
            final LoginDialog loginDialog = new LoginDialog(mainFrame);
            loginDialog.setVisible(true);
            
            final Optional<hr.algebra.model.User> user = loginDialog.getLoggedInUser();
            if (!user.isPresent()){
                System.exit(0);
            }
            
            mainFrame.login(user.get());
            
            }
        });        
    }
}
