//
//  detailViewController.swift
//  Lab Five
//
//  Created by Jennifer Mah on 3/16/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class detailViewController: UIViewController {

    
    @IBOutlet weak var date: UILabel!
    @IBOutlet weak var notes: UILabel!
    
    var entry:Journal?
    
    override func viewWillAppear(_ animated: Bool) {
        if let myEntry = entry{
            date.text = myEntry.getDate()
            notes.text = myEntry.entryNotes
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        
//    }
    

}
