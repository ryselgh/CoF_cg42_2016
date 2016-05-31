package com.server.model.board ;

import com.communication.board.AssistantDTO;

public class Assistant
{
	/**
	 *the constructor of the assistant
	 */
	public Assistant(){
		super();
	}

	public AssistantDTO toDTO(){
		return new AssistantDTO();
	}
}

