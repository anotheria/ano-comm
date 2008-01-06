/* ------------------------------------------------------------------------- *
$Source: /work/cvs/ano-comm/java/net/anotheria/communication/exceptions/MessagingServiceException.java,v $
$Author: lrosenberg $
$Date: 2008/01/06 19:51:32 $
$Revision: 1.2 $


Copyright 2004-2005 by FriendScout24 GmbH, Munich, Germany.
All rights reserved.

This software is the confidential and proprietary information
of FriendScout24 GmbH. ("Confidential Information").  You
shall not disclose such Confidential Information and shall use
it only in accordance with the terms of the license agreement
you entered into with FriendScout24 GmbH.
See www.friendscout24.de for details.
** ------------------------------------------------------------------------- */
package net.anotheria.communication.exceptions;

/**
 * TODO Please remind otoense to comment MessagingServiceException.java
 * @author otoense
 * Created on Mar 31, 2005
 */
public class MessagingServiceException extends Exception {

	
	public MessagingServiceException() {
		super();
	}
	
	public MessagingServiceException(String message) {
		super(message);
	}
	
	public MessagingServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MessagingServiceException(Throwable cause) {
		super(cause);
	}
}


/*
 * $Log: MessagingServiceException.java,v $
 * Revision 1.2  2008/01/06 19:51:32  lrosenberg
 * *** empty log message ***
 *
 * Revision 1.1  2005/03/31 10:46:51  lro
 * *** empty log message ***
 *
 */