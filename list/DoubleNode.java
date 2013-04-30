package list;

/*
 * Represents a single node in a doubly-linked list.
 * Adapted from The Harker School's Honors Data Structures project.
 */

public class DoubleNode
{
	private Object value;  //Value stored in this node
	private DoubleNode previous;
	private DoubleNode next;

	public DoubleNode(Object v)
	{
		value = v;
		previous = null;
		next = null;
	}

	/**
	 * Getter methods.
	 * @return	variable specified by method name
	 */
	public Object getValue()
	{
		return value;
	}
	
	public DoubleNode getPrevious()
	{
		return previous;
	}

	public DoubleNode getNext()
	{
		return next;
	}

	/**
	 * Setter methods.
	 * @param p	value to which to set variable specified by method name.
	 */
	public void setValue(Object v)
	{
		value = v;
	}

	public void setPrevious(DoubleNode p)
	{
		previous = p;
	}

	public void setNext(DoubleNode n)
	{
		next = n;
	}
}