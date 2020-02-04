//
//  FirstViewController.swift
//  Lab One
//
//  Created by Jennifer Mah on 2/2/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController ,UIPickerViewDelegate, UIPickerViewDataSource
{
    
//UI Items
    @IBOutlet weak var message: UILabel!
    @IBOutlet weak var animalPicker: UIPickerView!
    
    let animalTypeComponenet = 0
    let breedComponenet = 1
    var animalBreedsAndTypes = AnimalController()
    var AnimalTypes = [String]()
    var Breeds = [String]()
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        do{
            try animalBreedsAndTypes.LoadData()
            AnimalTypes = try animalBreedsAndTypes.GetAnimalTypes()
            Breeds = try animalBreedsAndTypes.getBreeds(idx:0)
        }catch{
            print(error)
        }
        
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == animalTypeComponenet{
            return AnimalTypes.count
        }else if component == breedComponenet{
            return Breeds.count
        }else{
            print("unknown component")
            return -1
        }
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
       
        if component == animalTypeComponenet{
            return AnimalTypes[row]
        }else if component == breedComponenet{
            return Breeds[row]
        }else{
            print("somthing went wrong")
            return("unknown")
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == animalTypeComponenet{
            do{
                Breeds = try animalBreedsAndTypes.getBreeds(idx:row)
            }catch{
                print(error)
            }
            animalPicker.reloadComponent(breedComponenet)
            animalPicker.selectRow(0, inComponent: breedComponenet, animated: true)
        }
        
        let animalTypeIndex = pickerView.selectedRow(inComponent: animalTypeComponenet)
        let breedIndex = pickerView.selectedRow(inComponent: breedComponenet)
        
        message.text = "You like \(AnimalTypes[animalTypeIndex].lowercased()) specifically a \(Breeds[breedIndex])."
    }

}

