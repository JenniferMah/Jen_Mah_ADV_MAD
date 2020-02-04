//
//  ThirdViewController.swift
//  Lab One
//
//  Created by Jennifer Mah on 2/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit
import AVFoundation

class ThirdViewController: UIViewController,AVAudioPlayerDelegate, AVAudioRecorderDelegate {
//UI elements
    @IBOutlet weak var RecordButton: UIButton!
    @IBOutlet weak var PlayButton: UIButton!
    @IBOutlet weak var StopButton: UIButton!
    
//var
    let audioSession = AVAudioSession.sharedInstance()
    var audioPlayer: AVAudioPlayer?
    var audioRecorder:AVAudioRecorder?
      
    let filename = "audio.m4a"
          
    
    
   
    override func viewDidLoad() {
        super.viewDidLoad()
        //path information to save file on phone
       let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
       let docDir = dirPath[0]
       let audioFilePath = docDir.appendingPathComponent(filename)
       
       //start to configure audio session
       do{
           try audioSession.setCategory(AVAudioSession.Category.playAndRecord, mode: .default, options: .init(rawValue:1))//adjust volume at end
       }catch{
           print(error)
       }
       
       //Create dictionary of settings for our audio reccorder
       let settings = [
           AVFormatIDKey: Int(kAudioFormatMPEG4AAC), //audio codec
           AVSampleRateKey: 1200, //sample rate
           AVNumberOfChannelsKey: 1,
           AVEncoderAudioQualityKey:AVAudioQuality.high.rawValue //bit rate
       ]
       
       do{
           audioRecorder = try AVAudioRecorder(url: audioFilePath, settings: settings)
           audioRecorder?.prepareToRecord()
           print("Ready to record")
           
       }catch{
           print(error)
       }

    }
    //Button Methods
    @IBAction func recordAudio(_ sender: Any) {
        if let recorder = audioRecorder {
            //check to make sure we aren't already recording
            if recorder.isRecording == false {
                //enable the stop button and start recording
                PlayButton.isEnabled = false
                StopButton.isEnabled = true
                recorder.delegate = self //allows recorder to respond to errors and complete the recording
                recorder.record()
            }
        } else {
            print("No audio recorder instance")
        }
    }
    @IBAction func playSound(_ sender: Any) {
        if audioRecorder?.isRecording == false{
            StopButton.isEnabled = true
            RecordButton.isEnabled = false

            do{
                try audioPlayer = AVAudioPlayer(contentsOf: (audioRecorder?.url)!)
                try audioSession.setCategory(AVAudioSession.Category.playback)
                audioPlayer!.delegate = self //respond to it's own events
                audioPlayer!.prepareToPlay() // preload audio
                audioPlayer!.play() //plays audio file
            }catch {
                print("can't play audio")
            }
        }
    }
    
    @IBAction func stopAudio(_ sender: Any) {
        StopButton.isEnabled = false
        PlayButton.isEnabled = true
        RecordButton.isEnabled = true
       //If it is reccording
       if audioRecorder?.isRecording == true {
           audioRecorder?.stop()
       }else{
           //it is playing
           audioPlayer?.stop()
           //reset session mode
           do{
               try audioSession.setCategory(AVAudioSession.Category.playAndRecord)
           }catch{
               print(error)
           }
       }
    }
    
    //audio player delegate method to change buttons when audio finishes playing
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        RecordButton.isEnabled = true
        StopButton.isEnabled = false
        //reset av session mode to optimize recording
        do {
            try audioSession.setCategory(AVAudioSession.Category.playAndRecord)
        } catch {
            print(error.localizedDescription)
        }
    }

}
