//
//  ViewController.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    //hold all my categories
    var categoriesList = ["Film", "Books", "Music", "Musicals & Theaters", "TV", "Video Games", "Board Games","Mythology", "Animals","Celebrities", "Comics", "Anime & Manga"]
    var selectedCategory = String()
    var dc = TriviaDataController()
    var data = [Trivia]()
    var catagoryName = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        dc.onDataUpdate = {[weak self] (data:[Trivia]) in self?.searchDone(triviaQuestions: data)}
        
    }
    
    func searchDone(triviaQuestions: [Trivia]) {
        data = triviaQuestions
        dismiss(animated: true, completion: nil)
        performSegue(withIdentifier: "gameSegue", sender: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        //check id of segue
        if segue.identifier == "gameSegue" {
            //downcast destination vc
            let Quiz = segue.destination as! QuestionViewController
            //set the title
            Quiz.title = "Trivia Night"
            //pass the data
            Quiz.results = data
            Quiz.category = catagoryName
        }
    }
    
    func numberItem(category: String)->String{
        catagoryName = category
        var categoryNumber = String()
        switch category{
        case "Film":
            categoryNumber = "11"
        case "Books":
            categoryNumber = "10"
        case "Music":
            categoryNumber = "12"
        case "Musicals & Theaters":
            categoryNumber = "13"
        case "TV":
            categoryNumber = "14"
        case "Video Games":
            categoryNumber = "15"
        case "Board Games":
            categoryNumber = "16"
        case "Mythology":
            categoryNumber = "20"
        case "Animals":
            categoryNumber = "27"
        case "Celebrities":
            categoryNumber = "26"
        case "Comics":
            categoryNumber = "29"
        case "Anime & Manga":
            categoryNumber = "31"
        default:
            categoryNumber = "0"
            print("Some other character")
        }
        return categoryNumber
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
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        //CASE STATEMENT FUNCTION
        let numberForURL = numberItem(category: categoriesList[indexPath.row])
        dc.loadJson(category: numberForURL)
        
    }

}

