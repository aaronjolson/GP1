
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010

 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.util.*;
import java.io.*;

/**
 * The collection class for Member objects
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class MemberList extends ItemList<Member, String> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List members = new LinkedList<Object>();
	private static MemberList memberList;

	private MemberList() throws Exception {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static MemberList instance() {
		try {
			if (memberList == null)
				return memberList = new MemberList();
		} catch (Exception e) {
			return null;
		}
		return memberList;
	}

	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberId
	 *            the id of the member
	 * @return true iff member exists
	 * 
	 */
	@Override
	public Member search(String value) {
		for (Member element : elements) {
			if (element.matches(value)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Inserts a member into the collection
	 * 
	 * @param member
	 *            the member to be inserted
	 * @return true iff the member could be inserted. Currently always true
	 */
	public boolean insertMember(Member item) {
		return super.insertItem(item);
	}

	public boolean removeMember(String value) {
		return super.removeItem(value);
	}

	public Iterator getMembers() {
		return super.getItems();
	}

	/*
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(memberList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (memberList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (memberList == null) {
					memberList = (MemberList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return members.toString();
	}
}