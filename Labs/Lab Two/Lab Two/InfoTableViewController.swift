//
//  InfoTableViewController.swift
//  Lab Two
//
//  Created by Jennifer Mah on 2/19/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class InfoTableViewController: UITableViewController {
    var month = String()
    var entryNumbers = String()
    
    @IBOutlet weak var monthName: UILabel!
    @IBOutlet weak var entryNumber: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(month)
        monthName.text = month
        entryNumber.text = entryNumbers
    }

}
