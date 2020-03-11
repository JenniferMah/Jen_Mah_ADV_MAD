//
//  ViewController.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate,  UITableViewDataSource {
  
    
    //hold all my categories
    var categoriesList = ["Film", "Books", "Music", "Musicals & Theaters", "TV", "Video Games", "Board Games","Mythology", "Animals","Celebrities", "Comics", "Anime & Manga"]
    var selectedCategory = String()
    var dc = TriviaDataController()
    var data = [Trivia]()
    var catagoryName = String()
    
    
    //TableView
    let tableview: UITableView = {
        let tv = UITableView()
        tv.backgroundColor = UIColor.white
        tv.translatesAutoresizingMaskIntoConstraints = false
        tv.separatorColor = UIColor.white
        return tv
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        title = "Trivia Categories"
        setupTableView()
        dc.onDataUpdate = {[weak self] (data:[Trivia]) in self?.searchDone(triviaQuestions: data)}
    }
    
    func setTableViewDelegates(){
        tableview.delegate = self
        tableview.dataSource = self
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
            Quiz.title = catagoryName
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
    
    func setupTableView() {
        setTableViewDelegates()
        tableview.register(categoryCell.self, forCellReuseIdentifier: "cellId")
        
        view.addSubview(tableview)
        
        NSLayoutConstraint.activate([
            tableview.topAnchor.constraint(equalTo: self.view.topAnchor),
            tableview.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            tableview.rightAnchor.constraint(equalTo: self.view.rightAnchor),
            tableview.leftAnchor.constraint(equalTo: self.view.leftAnchor)
        ])
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return categoriesList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! categoryCell
        cell.backgroundColor = UIColor.white
        cell.cellLabel.text = categoriesList[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let numberForURL = numberItem(category: categoriesList[indexPath.row])
        dc.loadJson(category: numberForURL)

    }

}

