package com.dark.rs2.content.dialogue.impl;

import com.dark.rs2.content.dialogue.Dialogue;
import com.dark.rs2.content.dialogue.DialogueConstants;
import com.dark.rs2.content.dialogue.DialogueManager;
import com.dark.rs2.content.dialogue.Emotion;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendEnterString;
import com.dark.rs2.entity.player.net.out.impl.SendRemoveInterfaces;

/**
 * Handles Gambler dialogue
 * @author Daniel
 *
 */
public class GamblerDialogue extends Dialogue {
	
	public GamblerDialogue(Player player) {
		this.player = player;
	}

    @Override
    public boolean clickButton(int id) {
    
       switch (id) {
       
       case DialogueConstants.OPTIONS_3_1:
    	   setNext(2);
    	   execute();
    	   break;
    	   
       case DialogueConstants.OPTIONS_3_2:
    	   setNext(4);
    	   execute();
    	   break;
       
       case DialogueConstants.OPTIONS_4_3:
    	  // setNext(2);
    	   execute();
    	   break;
    	   
       case DialogueConstants.OPTIONS_3_3:
    	   player.send(new SendRemoveInterfaces());
    	   break;
    	   
       }
       
        return false;
    }

    @Override
    public void execute() {
    
       switch (next) {
       
       case 0:
    	   DialogueManager.sendNpcChat(player, 1011, Emotion.HAPPY_TALK, "Hello " + player.getUsername() + "!", "I am in charge of all gambling done in Horizon-OS.");
    	   next++;
    	   break;
    	   
       case 1:
    	   DialogueManager.sendOption(player, "Play 55x2", "Lottery", "Nevermind");
    	   break;
    	   
       case 2:
    	   DialogueManager.sendNpcChat(player, 1011, Emotion.HAPPY_TALK, "How much would you like to gamble?");
    	   next++;
    	   break;
    	   
       case 3:
			player.setEnterXInterfaceId(56000);
			player.getClient().queueOutgoingPacket(new SendEnterString());
    	   break;
    	   
       case 4:
    	   player.start(new LotteryDialogue(player));
    	   break;
    	   
       
       }
       
    }

}