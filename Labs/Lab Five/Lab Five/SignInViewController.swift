//
//  SignInViewController.swift
//  Lab Five
//
//  Created by Jennifer Mah on 3/16/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit
import FirebaseUI

class SignInViewController: UIViewController, FUIAuthDelegate {
    let authUI = FUIAuth.defaultAuthUI()

    @IBAction func SignIn(_ sender: Any) {
        let authViewController = authUI?.authViewController()
        present(authViewController!,animated: true, completion: nil)
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        authUI?.delegate = self
        
        let provider : [FUIAuthProvider] = [FUIGoogleAuth()] //THIS IS WHERE TO ADD MORE auth
        authUI?.providers = provider
    }
    
    func authUI(_ authUI: FUIAuth, didSignInWith user: User?, error: Error?) {
        //make sure we got the user object
        if user != nil {
            //get access to storyboard
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            //get instance of the root nav controller
            let vc = storyboard.instantiateViewController(withIdentifier: "rootNav")
            //set it to full screen instead of popover
            vc.modalPresentationStyle = .fullScreen
            //present the root view controller
            present(vc, animated: true, completion: nil)
        } else {
            print(error!.localizedDescription)
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
