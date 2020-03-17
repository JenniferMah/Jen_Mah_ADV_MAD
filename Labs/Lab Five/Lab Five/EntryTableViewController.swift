//
//  EntryTableViewController.swift
//  Lab Five
//
//  Created by Jennifer Mah on 3/16/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class EntryTableViewController: UITableViewController {
    var DiaryDC = DiaryDataController()
    var diaryData = [Journal]()

    override func viewDidLoad() {
        super.viewDidLoad()

        DiaryDC.onDataUpdate = {[weak self] (data: [Journal]) -> Void in self?.newData(data: data)}
        
        DiaryDC.loadData()
    }

    func newData(data: [Journal]) {
        diaryData = data
        diaryData.sort(by: {(r1, r2) in r1.date.compare(r2.date) == .orderedDescending })
        tableView.reloadData()
    }
    
    @IBAction func unwindSegue(segue: UIStoryboardSegue) {
        if segue.identifier == "Save" {
            let sourceVC = segue.source as! addEntryViewController
            //double check that we have values
            if let userDate = sourceVC.date {
                //check the notes
                if let userNotes = sourceVC.entryNotes {
                    //write the data
                    DiaryDC.writeData(date: userDate, notes: userNotes)
                } else {
                    //write data with empty notes
                    DiaryDC.writeData(date: userDate, notes: "")
                }
            }
        }
    }
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return diaryData.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "DayCell", for: indexPath)

        //get the run object
        let day = diaryData[indexPath.row]
        //set the labels
        cell.textLabel?.text = day.getDate()
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "DetailView"{
            let vc = segue.destination as! detailViewController
            let idx = tableView.indexPath(for: (sender as! UITableViewCell))
            vc.entry = diaryData[idx!.row]
        }
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

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

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    
    

}
