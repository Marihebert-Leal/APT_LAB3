package mp3playermock;

/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import java.util.ArrayList;

  /** 
   * Begin playing the filename at the top of the
   * play list, or do nothing if playlist 
   * is empty. 
   */
public class MockMp3Player implements Mp3Player {
	// State of the player
	private boolean isPlaying = false;
	// Position within the current song
	private double currentPos = 0.0;
	// List of song names
	private ArrayList songList = new ArrayList();
	// Index of current song
	private int currentIndex;
	public void play(){
		if (songList.size() > 0) {
			isPlaying = true;

			currentPos = 1.0;
		} else {
			isPlaying = false;
			currentPos = 0.0;
		}
	}

	public void pause() {
		isPlaying = false;
	}

  
	public void stop() {
		isPlaying = false;
	// Rewind to beginning of current song
		currentPos = 0.0;
	}
  
	public double currentPosition() {
		return currentPos;
	}

	public String currentSong() {
		if (songList.size() == 0) {
			return null;
		}
		return (String)songList.get(currentIndex);
	 }
  
	public void next(){
		if (currentIndex < songList.size()-1) {
			currentIndex++;
		}
		currentPos = 0.0;
	}

 
	public void prev(){
		if (currentIndex > 0) {
			currentIndex--;
		}
		currentPos = 0.0;
	}
	public boolean isPlaying() {
		return isPlaying;
	}

	public void loadSongs(ArrayList names){
		songList = names;
	}
  }

