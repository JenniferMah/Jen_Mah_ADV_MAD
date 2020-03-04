//
//  DrinksDataController.swift
//  Lab Four
//
//  Created by Jennifer Mah on 3/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation

class DrinksDataController{
    var currentDrinks = DrinksData()
    var onDataUpdate: ((_ data: [Drinks]) -> Void)?

    
    func loadJson(drinkCategory: String) {
        let urlPath = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=\(drinkCategory)"
        
        guard let url = URL(string: urlPath) else {
            print("bad url")
            return
        }

        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            guard statusCode == 200 else {
                print("file download problem. status code: \(statusCode)")
                return
            }
            print("download finished")
            DispatchQueue.main.async {self.parseJson(rawData: data!)}
        })
        session.resume()
    }

    func parseJson(rawData: Data)  {
        do {
           //try to decode the response
           let parsedData = try JSONDecoder().decode(DrinksData.self, from: rawData)
           currentDrinks.drinks.removeAll()
           //add all the campsite entries to our class property that stores the current campsites
           for drinks in parsedData.drinks {
               currentDrinks.drinks.append(drinks)
           }
        } catch {
           //something went wrong parsing the data — throw error!
           print("json error")
           print(error)
        }
        onDataUpdate?(currentDrinks.drinks)
    }
    
}
