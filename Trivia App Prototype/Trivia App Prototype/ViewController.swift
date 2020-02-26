//
//  ViewController.swift
//  Trivia App Prototype
//
//  Created by Jennifer Mah on 2/24/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    var categoriesList = ["Film","Board Game", "Books"] //hold all my categories

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
           return categoriesList.count
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
           let cell = tableView.dequeueReusableCell(withIdentifier: "category",for:indexPath)
           //Set text label based on idex of cell
           cell.textLabel?.text = categoriesList[indexPath.row]
           return cell
    }
}

