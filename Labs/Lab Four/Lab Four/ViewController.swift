//
//  ViewController.swift
//  Lab Four
//
//  Created by Jennifer Mah on 3/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
 
   let drinkOptions = ["Margarita", "Gin", "Daiquiri", "Vodka", "Martini"]
   var selectedDrink = String()
   var drinksDC = DrinksDataController()
   var data = [Drinks]()
   
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        selectedDrink = drinkOptions[0]
        drinksDC.onDataUpdate = {[weak self] (data:[Drinks]) in self?.searchDone(drinks: data)}
    }

    func searchDone(drinks: [Drinks]) {
        dismiss(animated: true, completion: nil)
        data = drinks
        performSegue(withIdentifier: "SearchResults", sender: nil)
    }
    
    @IBAction func searchDrinks(_ sender: Any) {
        drinksDC.loadJson(drinkCategory: selectedDrink)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SearchResults" {
            let resultsVC = segue.destination as! ResultsTableViewController
            resultsVC.title = "\(selectedDrink) Drinks"
            resultsVC.results = data
        }
    }
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
         1
     }
     
     func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return drinkOptions.count
     }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return drinkOptions[row]
    }

    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectedDrink = drinkOptions[row]
    }
     

}

