package swingdudu;

import java.awt.Graphics;


public class Book {
	String name;
	int bnum;
	String aut;
	
	public Book(String n, int b, String a)
	{
		name = n;
		bnum = b;
		aut  = a;
	}
}

class	Librarian
{
	int user;
	int pass;
	
	public Librarian(int u, int p)
	{
		user = u;
		pass = p;
	}

	public boolean login(Graphics g, int u, int p)
	{
		if(u == user)
		{
			g.drawString("Enter the password", 10, 10);
		}
		return true;
	}
	public void addStudent()
	{
	}

	public void delStudent()
	{
	}
	
	public void addBook()
	{
	}

	public void delBook()
	{
	}
}
