//
//  categoryCell.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/10/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class categoryCell: UITableViewCell {
    
    let cellLabel: UILabel = {
        let category = UILabel()
        category.font = UIFont.boldSystemFont(ofSize: 17)
        category.textColor = UIColor.white
        category.translatesAutoresizingMaskIntoConstraints = false
        return category
    }()
        
    let TVcellView: UIView = {
           let view = UIView()
           view.layer.cornerRadius = 15
           view.backgroundColor = UIColor(hue: 0.5028, saturation: 0.73, brightness: 0.78, alpha: 1.0)
           view.translatesAutoresizingMaskIntoConstraints = false
           return view
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setup()
    }
    
    func setup() {
        addSubview(TVcellView)
        TVcellView.addSubview(cellLabel)
        self.selectionStyle = .none
        
        //cell view constraints
        NSLayoutConstraint.activate([
            TVcellView.leftAnchor.constraint(equalTo: self.leftAnchor, constant: 5),
            TVcellView.rightAnchor.constraint(equalTo: self.rightAnchor, constant: -5),
            TVcellView.topAnchor.constraint(equalTo: self.topAnchor, constant: 10),
            TVcellView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
        //label constraints
        cellLabel.widthAnchor.constraint(equalToConstant: 200).isActive = true
        cellLabel.heightAnchor.constraint(equalToConstant: 150).isActive = true
        cellLabel.leftAnchor.constraint(equalTo: TVcellView.leftAnchor, constant: 25).isActive = true
        cellLabel.centerYAnchor.constraint(equalTo: TVcellView.centerYAnchor).isActive = true

        
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
