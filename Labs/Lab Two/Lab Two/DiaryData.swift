//
//  DiaryData.swift
//  Lab Two
//
//  Created by Jennifer Mah on 2/18/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation
import UIKit

//we are going to use this struct to decode and encode data

struct DiaryDataModel: Codable {
    var entry: [String]
    var month: String
}

enum DataError: Error {
    case NoDataFile
    case CouldNotDecode
    case CouldNotEncode
}


class DiaryDataController {
    var allData = [DiaryDataModel]() //MARK:CHANGE THIS
    let fileName = "months"
    let dataFileName = "data.plist" //data persistance
    
    init() {
        //get our app instance
        let app = UIApplication.shared
        //subscribe to willResignActive notification
        NotificationCenter.default.addObserver(self, selector: #selector(DiaryDataController.writeData(_:)), name: UIApplication.willResignActiveNotification, object: app)
    }
    
    //load data from plist
    func loadData() throws {
        let pathURL: URL?
        
        //get the path where our data file would be
        let dataFileURL = getDataFile(datafile: dataFileName)
        
        //check to see if the data file exists
        if FileManager.default.fileExists(atPath: dataFileURL.path) {
            pathURL = dataFileURL
        } else {
            //load default data if we can't find a user data file
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
            
        }

        //check for file and get URL if possible
        if let dataURL = pathURL {
            let decoder = PropertyListDecoder()
            do {
                //get byte buffer (raw data)
                let data = try Data(contentsOf: dataURL)
                //decode to our model
                allData = try decoder.decode([DiaryDataModel].self, from: data)
            } catch {
                throw DataError.CouldNotDecode
            }
        }
        else {
            //couldn't get path
            throw DataError.NoDataFile
        }
    }
    
    func getDataFile(datafile: String) -> URL {
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        
        // URL for our plist
        return docDir.appendingPathComponent(datafile)
    }

    
    @objc func writeData(_ notification: NSNotification) throws {
        let dataFileURL = getDataFile(datafile: dataFileName)
        //get an encoder
        let encoder = PropertyListEncoder()
        //set format — plist is a type of xml
        encoder.outputFormat = .xml
        do {
            let data = try encoder.encode(allData.self)
            try data.write(to: dataFileURL)
        } catch {
            print(error)
            throw DataError.CouldNotEncode
        }
        
    }

    
    
    
    
    
//    MARK:Methods
    
    func getMonths() -> [String] {
        //init empty array
        var allMonths = [String]()
        //loop through data and append to array
        for item in allData {
            allMonths.append(item.month)
        }
        return allMonths
    }
    
   
    func getEntry(idx: Int) -> [String] {
        return allData[idx].entry
    }
    
    func addEntry(monthIdx: Int, newEntry: String, entryIdx: Int) {
        allData[monthIdx].entry.insert(newEntry, at: entryIdx)
    }
    
    func deleteEntry(monthIdx: Int, entryIdx: Int) {
        allData[monthIdx].entry.remove(at: entryIdx)
    }
}
