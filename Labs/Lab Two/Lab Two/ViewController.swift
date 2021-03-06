//
//  ViewController.swift
//  Lab Two
//
//  Created by Jennifer Mah on 2/18/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    var monthList = [String]()
    var diaryDataController = DiaryDataController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Make title large
        navigationController?.navigationBar.prefersLargeTitles = true
        
        do {
            try diaryDataController.loadData()
            monthList = diaryDataController.getMonths()
        } catch {
            print(error)
        }
    }
    

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return monthList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "monthCell", for: indexPath)
        cell.textLabel?.text = monthList[indexPath.row]
        return cell
    }


    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "entrySegue" {
            let detailVC = segue.destination as! DetailTableViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)
            if let selection = indexPath?.row {
                detailVC.selectedMonth = selection
                detailVC.title = monthList[selection]
                detailVC.diaryData = diaryDataController
            }
        }else if segue.identifier == "moreInfo" {
            let infoVC = segue.destination as! InfoTableViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)
            infoVC.month = monthList[indexPath!.row]
            let entryList = diaryDataController.getEntry(idx: indexPath!.row)
            infoVC.entryNumbers = String(entryList.count)
            infoVC.title = monthList[indexPath!.row]
        }
    }



}

