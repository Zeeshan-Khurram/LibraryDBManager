package swingdudu;



import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.*;
import java.util.*;
 
public class libManSys {
 
    public static void main(String[] args) throws IOException {
    	ArrayList<String> deeBooks = new ArrayList<String>();
    	ArrayList<String> deeStudents = new ArrayList<String>();
    	
		
		Scanner bin = null;
		try {
			bin = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaBooks.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner sin = null;
		try {
			sin = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaStudents.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(bin.hasNext())
		{
			deeBooks.add(bin.nextLine());
		}
		
		while(sin.hasNext())
		{
			deeStudents.add(sin.nextLine());
		}
    	
		ArrayList<String> deeTransfer = new ArrayList<String>();
		Scanner tin = new Scanner(new File("F:\\Library\\src\\swingdudu\\TransferPage.txt"));
		while(tin.hasNextLine())
		{
			deeTransfer.add(tin.nextLine());
		}
    	
    	Admin admin = new Admin();
    	Student student = new Student();
        
        final JFrame frame = new JFrame("Student/Admin Login");
       
        JLabel lblUser = new JLabel("User Name:");
		JTextField tfUser = new JTextField(20);
		lblUser.setLabelFor(tfUser);

		JLabel lblPassword = new JLabel("Password:");
		final JPasswordField pfPassword = new JPasswordField(20);
		lblPassword.setLabelFor(pfPassword);
		
		 JButton btnGet = new JButton("Display Password");
	        btnGet.addActionListener(
	                new ActionListener() {
	 
	                    public void actionPerformed(ActionEvent e) {
	                        String password = new String(pfPassword.getPassword());
	                        JOptionPane.showMessageDialog(frame,
	                                "Password is " + password);
	                    }
	                });
	        JButton btnLogin = new JButton("Login");
	        
	        JPanel panel = new JPanel();
	        panel.setLayout(new SpringLayout());
	 
	        panel.add(lblUser);
	        panel.add(tfUser);
	        panel.add(lblPassword);
	        panel.add(pfPassword);
	        panel.add(btnLogin);
	        panel.add(btnGet);
	 
	        SpringUtilities.makeCompactGrid(panel,
	                3, 2, 
	                6, 6, 
	                6, 6);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        btnLogin.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e ) {
        				String p = new String(pfPassword.getPassword());
        				if(p.equals("123456")) {
        					frame.setVisible(false);
        					admin.makeAdmin(deeBooks, deeStudents, deeTransfer);
        				}
        				else 
        				{
        					boolean done = false;
        					for(int x = 0; x < deeStudents.size(); x+=3)
        					{
        						if(deeStudents.get(x).equals(tfUser.getText()))
        						{
        							if(deeStudents.get(x+1).equals(p))
        							{
        								done = true;
        								frame.setVisible(false);
        	        					try {
											student.makeStudent(deeBooks, deeStudents, deeTransfer, (x+2));
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
        							}
        						}
        					}
        					if(!done)
        						JOptionPane.showMessageDialog(frame, "Wrong Password, Try Again");
        				}

        					
        			}
        		});
    }
}

//_______________________________________________________________________________________________________________________________________

class Admin
{
	final JFrame frame = new JFrame("AdminFront");
	final JFrame students = new JFrame("List of Students");
	final JFrame books = new JFrame("List of Books");
	final JFrame addS = new JFrame("Add a Student");
	final JFrame addB = new JFrame("Add a Book");
	final JFrame checkT = new JFrame("The List of Checked Out Books");
	
	JButton studentList = new JButton("STUDENTS");
	JButton bookList = new JButton("BOOKS");
	JButton transferList = new JButton("Checked Out Books");
	JPanel panel = new JPanel();
	
	int numberOfBooks;
	
	ArrayList<String> arrBooks;
	ArrayList<String> arrStudents;
	ArrayList<String> arrTransfer;

	public Admin()
	{
		 
	}
	
	public void makeAdmin(ArrayList<String> aB, ArrayList<String> aS, ArrayList<String> aT)
	{
		arrBooks = aB;
		arrStudents = aS;
		arrTransfer = aT;
		 
        panel.add(bookList);
        panel.add(studentList);
        panel.add(transferList);
 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 150);
	    frame.getContentPane().add(panel);
	    frame.setVisible(true);
	    
	    studentList.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    	try {
							createStudentsList();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }
                });
	    
	    bookList.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    	try {
							createBooksList();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }
                });
	    
	    transferList.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    	try {
							createTransferList();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }
                });
	}
	
	public void createStudentsList() throws IOException
	{
		students.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		students.setSize(300, 500);
		students.setVisible(true);
		
		JTextArea bob = new JTextArea(5,20);
    	Scanner in = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaStudents.txt"));
    	String b = "";
    	int x = 0;
    	while(in.hasNextLine())
    	{
    		b += in.nextLine() + "\n";
    		x++;
    		if(x % 3 == 0)
    			b += "\n";
    	}
    	bob.setText(b);
    	bob.setEditable(false);
    	
		JPanel sPanel =  new JPanel();
		JButton sBack = new JButton("Back");
		JButton addStudent = new JButton("ADD STUDENT");	
				addStudent.addActionListener(
		                new ActionListener() {
		 
		                    public void actionPerformed(ActionEvent e) {
		                        try {
									addStu();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		                        students.setVisible(false);
		                    }
		                });
				
				sBack.addActionListener(
		                new ActionListener() {
		 
		                    public void actionPerformed(ActionEvent e) {
		                        students.setVisible(false);
		                        frame.setVisible(true);
		                        frame.repaint();
		                    }
		                });
				
		sPanel.add(sBack);
		sPanel.add(addStudent);
		sPanel.add(bob);
		students.add(sPanel);
	}
	
	public void createBooksList() throws IOException
	{
		books.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		books.setSize(300, 500);
		books.setVisible(true);
		
		JTextArea bob = new JTextArea(5,20);
    	Scanner in = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaBooks.txt"));
    	String b = "";
    	int x = 0;
    	while(in.hasNextLine())
    	{
    		b += in.nextLine() + "\n";
    		x++;
    		if(x % 3 == 0)
    			b += "\n";
    	}
    	bob.setText(b);
    	bob.setEditable(false);

		JButton addBook = new JButton("ADD BOOK");
				addBook.addActionListener(
		                new ActionListener() {
		 
		                    public void actionPerformed(ActionEvent e) {
		                        books.setVisible(false);
		                        try {
									addBo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		                    }
		                });
		JPanel bPanel =  new JPanel();
		JButton bBack = new JButton("Back");
				bBack.addActionListener(
		                new ActionListener() {
		 
		                    public void actionPerformed(ActionEvent e) {
		                        books.setVisible(false);
		                        frame.setVisible(true);
		                        frame.repaint();
		                    }
		                });
		
		bPanel.add(bBack);
		bPanel.add(addBook);
		bPanel.add(bob);
		books.add(bPanel);
		
	}
	
	public void createTransferList() throws IOException
	{
		checkT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkT.setSize(300, 500);
		checkT.setVisible(true);
		
		JTextArea bob = new JTextArea(5,20);
    	Scanner in = new Scanner(new File("F:\\Library\\src\\swingdudu\\TransferPage.txt"));
    	String b = "";
    	int x = 0;
    	while(in.hasNextLine())
    	{
    		b += in.nextLine() + "\n";
    		x++;
    		if(x % 4		 == 0)
    			b += "\n";
    		
    	}
    	bob.setText(b);
    	bob.setEditable(false);

		JPanel ttt = new JPanel();
		JButton tBack = new JButton("Back");
		tBack.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        checkT.setVisible(false);
                        frame.setVisible(true);
                        frame.repaint();
                    }
                });
		
		ttt.add(tBack);
		ttt.add(bob);
		checkT.add(ttt);
		
	}

	public void addStu() throws IOException
	{
		addS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addS.setSize(300, 200);
		addS.setVisible(true);
		
		FileWriter studentWriter = new FileWriter("F:\\Library\\src\\swingdudu\\DaStudents.txt",true);
	    PrintWriter pStudentWriter = new PrintWriter(studentWriter);
	    
		JPanel sss = new JPanel();
		JButton stuBack = new JButton("Back");
		stuBack.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        addS.setVisible(false);
                        students.setVisible(true);
                    }
                });
		
		JLabel addStuUser = new JLabel("User Name:");
		JTextField StuUser = new JTextField(25);
		addStuUser.setLabelFor(StuUser);
		
		JLabel addStuPassword = new JLabel("Password:");
		JPasswordField StuPassword = new JPasswordField(25);
		addStuPassword.setLabelFor(StuPassword);
		
		JLabel addStuName = new JLabel("Name");
		JTextField StuName = new JTextField(25);
		addStuName.setLabelFor(StuName);
		
		JButton stuAdd = new JButton("ADD");
		stuAdd.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        pStudentWriter.println(StuUser.getText());
                        pStudentWriter.println(StuPassword.getPassword());
                        pStudentWriter.println(StuName.getText());
                        pStudentWriter.close();
                        arrStudents.add(StuUser.getText());
                        arrStudents.add(new String(StuPassword.getPassword()));
                        arrStudents.add(StuName.getText());
                        StuUser.setText("");
                        StuPassword.setText("");
                        StuName.setText("");
                        
                        
                    }
                });
		
		
		sss.add(stuBack);
		sss.add(stuAdd);
		sss.add(addStuUser);
		sss.add(StuUser);
		sss.add(addStuPassword);
		sss.add(StuPassword);
		sss.add(addStuName);
		sss.add(StuName);
		
		addS.add(sss);
	}
	
	public void addBo() throws IOException
	{
		addB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addB.setSize(300, 200);
		addB.setVisible(true);
		
		FileWriter bookWriter = new FileWriter("F:\\Library\\src\\swingdudu\\DaBooks.txt",true);
	    PrintWriter pBookWriter = new PrintWriter(bookWriter);
		
		JPanel bbb = new JPanel();
		JButton boBack = new JButton("Back");
		boBack.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                        addB.setVisible(false);
                        books.setVisible(true);
                       
                    }
                });
		
		JLabel addBName = new JLabel("NAME OF BOOK");
		JTextField BName = new JTextField(25);
		addBName.setLabelFor(BName);
		
		JLabel addAuthor = new JLabel("AUTHOR:");
		JTextField Author = new JTextField(25);
		addAuthor.setLabelFor(Author);
		
		JButton boAdd = new JButton("ADD");
		boAdd.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                    	numberOfBooks = (arrBooks.size()/3);
                    	numberOfBooks++;
                    	DecimalFormat bb = new DecimalFormat("0000");
                    	pBookWriter.println(bb.format(numberOfBooks));
                    	pBookWriter.println(BName.getText());
                        pBookWriter.println(Author.getText());
                        pBookWriter.close();
                        arrBooks.add(BName.getText());
                        arrBooks.add(Author.getText());
                        BName.setText("");
                        Author.setText("");
                    }
                });
		
		
		bbb.add(boBack);
		bbb.add(boAdd);
		bbb.add(addBName);
		bbb.add(BName);
		bbb.add(addAuthor);
		bbb.add(Author);
		
		addB.add(bbb);
	}
}
//_______________________________________________________________________________________________________________________________________

class Student
{
	final JFrame frame = new JFrame("StudentFront");
	
	ArrayList<String> arrBooks;
	ArrayList<String> arrStudents;
	ArrayList<String> arrTransfer;
	
	String student;
	
	public Student()
	{
		
	}
	
	public void makeStudent(ArrayList<String> aB, ArrayList<String> aS, ArrayList<String> aT, int x) throws IOException
	{
		arrBooks = aB;
		arrStudents = aS;
		arrTransfer = aT;
		
		student = arrStudents.get(x);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 1000);
	    frame.setVisible(true);
	    
	    JTextArea bob = new JTextArea(5,20);
    	Scanner in = null;
		try {
			in = new Scanner(new File("F:\\Library\\src\\swingdudu\\DaBooks.txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	String b = "";
    	int xr = 0;
    	while(in.hasNextLine())
    	{
    		b += in.nextLine() + "\n";
    		xr++;
    		if(xr % 3 == 0)
    			b += "\n";
    		
    	}
    	bob.setText(b);
    	bob.setEditable(false);
    	
	    JPanel ps = new JPanel();
	    JLabel cO = new JLabel("CHECK OUT");
	    JTextField checkk = new JTextField(25);
	    cO.setLabelFor(checkk);
	    JButton checkOut = new JButton("SELECT");
	    checkOut.addActionListener(
                new ActionListener() {
 
                    public void actionPerformed(ActionEvent e) {
                    	if (checkk.getText().matches("[0-9]+") && checkk.getText().length() < 2  && Integer.parseInt(checkk.getText()) <= (arrBooks.size()/3))
                    	{
                    		String bob = checkk.getText();
	                    	int bbob = (Integer.parseInt(bob) - 1) * 3;
	                    	String yah = arrBooks.get(bbob);
	                    	String yah1 = arrBooks.get(bbob+1);
	                    	String yah2 = arrBooks.get(bbob +2);
	                    	FileWriter fileWriter = null;
							try {
								fileWriter = new FileWriter("F:\\Library\\src\\swingdudu\\TransferPage.txt", true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} //Set true for append mode
	                	    PrintWriter printWriter = new PrintWriter(fileWriter);
	                	    printWriter.println(student);  //New line
	                	    printWriter.println(yah);
	                	    printWriter.println(yah1);
	                	    printWriter.println(yah2);
	                	    printWriter.close();             	    
	                	    JOptionPane.showMessageDialog(frame, "Yay u have selected the book \" " + yah1 + "\", Just remember that");
	                	    checkk.setText("");
                    	}
                    	else
                    		JOptionPane.showMessageDialog(frame,
	                                "Please enter an integer for the number of book you want");
                    }
                });
	    
	    ps.add(cO);
	    ps.add(checkk);
	    ps.add(checkOut);
	    ps.add(bob);
	    
	    frame.add(ps);
	}
}
