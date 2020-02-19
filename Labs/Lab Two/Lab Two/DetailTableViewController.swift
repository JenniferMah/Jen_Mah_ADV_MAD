//
//  DetailTableViewController.swift
//  Lab Two
//
//  Created by Jennifer Mah on 2/18/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class DetailTableViewController: UITableViewController {
    
    var diaryData = DiaryDataController()
    var selectedMonth = 0
    var entryList = [String]()
    var fillterEntries = [String]()
    
    //add instance of seach controller
    var searchController = UISearchController()


    override func viewWillAppear(_ animated: Bool) {
        entryList = diaryData.getEntry(idx: selectedMonth)
        
        let resultsController = SearchResultsController()
        resultsController.allEntries = entryList
        
        searchController = UISearchController(searchResultsController: resultsController)
        searchController.searchBar.placeholder = "Search Entries"
        searchController.searchBar.sizeToFit()
        
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController
        
        
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return entryList.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "entryCell", for: indexPath)
        cell.textLabel?.text = entryList[indexPath.row]
        return cell
    }


    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            entryList.remove(at: indexPath.row)
            diaryData.deleteEntry(monthIdx: selectedMonth, entryIdx: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    
    

    @IBAction func unwindSegue (_ segue:UIStoryboardSegue){
        //check the id of segue
        if segue.identifier == "save" {
            //downcast to access members
            let source = segue.source as! AddEntryViewController
            
            //double check to make sure new country name is not empty
            if source.addedEntry.isEmpty == false {
                //add new country to data model (notify of changes)
                diaryData.addEntry(monthIdx: selectedMonth, newEntry: source.addedEntry, entryIdx: entryList.count)
                //add to working copy
                entryList.append(source.addedEntry)
                //update table view based on data changes
                tableView.reloadData()
            }
        }
    }


    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
