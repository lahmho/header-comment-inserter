/**
 *   (C) Smart Physics
 *  www.smartphysics.net
 *  
 * @author: Lam Ho
 * @since: Jul 5, 2012
 */
package vn.greewoo.headercommentinserter;

import vn.greewoo.headercommentinserter.HeaderCommentInserter.InputType;

/**
 * 
 */

public class InputTypeBuilder {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private InputType type;
	private String comment;
	private int ndeletedline;

	// ===========================================================
	// Constructors
	// ===========================================================

	public InputTypeBuilder() {
		super();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public InputTypeBuilder setType(InputType type) {
		this.type = type;
		return this;
	}

	public InputTypeBuilder setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public InputTypeBuilder setNumberOfDeleteLine(int n) {
		this.ndeletedline = n;
		return this;
	}

	public InputType getType() {
		return type;
	}

	public String getComment() {
		return comment;
	}

	public int getNumberOfDeleteLine() {
		return ndeletedline;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

}
