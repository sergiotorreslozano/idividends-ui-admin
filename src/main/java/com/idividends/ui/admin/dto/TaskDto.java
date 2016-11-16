package com.idividends.ui.admin.dto;

public class TaskDto {

	private TaskResult result;

	/**
	 * 
	 */
	public TaskDto() {
		super();
		// From the beginning there will be a result
		result = TaskResult.ERROR_NO_RESULT;
	}

	/**
	 * @return the result
	 */
	public TaskResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(TaskResult result) {
		this.result = result;
	}

	public String toString() {
		if (result != null) {
			return "Code: " + result.getCode() + " message: " + result.getMessage();
		}
		return null;
	}

}

