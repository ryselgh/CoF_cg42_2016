package com.server.model.board ;

import com.communication.board.AssistantDTO;


/**
 * The Class Assistant.
 */
public class Assistant
{
	
	/**
	 * the constructor of the assistant.
	 */
	public Assistant(){
		super();
	}

	/**
	 * To dto.
	 *
	 * @return the assistant dto
	 */
	public AssistantDTO toDTO(){
		return new AssistantDTO();
	}
}

