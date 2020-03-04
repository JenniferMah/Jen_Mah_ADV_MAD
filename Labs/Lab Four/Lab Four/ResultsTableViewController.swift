//
//  ResultsTableViewController.swift
//  Lab Four
//
//  Created by Jennifer Mah on 3/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ResultsTableViewController: UITableViewController {
    
    var results = [Drinks]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return results.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "DrinksCell", for: indexPath)

        // Configure the cell...
        cell.textLabel!.text = results[indexPath.row].strDrink

        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
       if segue.identifier == "TEST" {
            let idx = tableView.indexPath(for: sender as! UITableViewCell)!.row
            let selectedDrink = results[idx]
            let detailVC = segue.destination as! DetailViewController
            //set detail vc properties
            detailVC.drinkName = selectedDrink.strDrink
            detailVC.drinkPic = selectedDrink.strDrinkThumb
            detailVC.drinkInstructions = selectedDrink.strInstructions
        }
    }
    

}
