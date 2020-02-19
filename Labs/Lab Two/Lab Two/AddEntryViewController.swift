//
//  AddEntryViewController.swift
//  Lab Two
//
//  Created by Jennifer Mah on 2/18/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class AddEntryViewController: UIViewController {

    @IBOutlet weak var entryTextfield: UITextField!
    
    var addedEntry = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
           if segue.identifier == "save" {
               //make sure they entered info
               if entryTextfield.text?.isEmpty == false {
                   addedEntry = entryTextfield.text!
               }
           }

    }


}
