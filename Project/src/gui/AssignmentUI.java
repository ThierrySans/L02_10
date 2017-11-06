//package gui;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.RowLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Group;
//import org.eclipse.swt.widgets.Shell;
//
//import zodiac.action.QuestionAction;
//import zodiac.action.StudentAction;
//import zodiac.definition.Student;
//import zodiac.definition.coursework.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.TreeMap;
//
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Listener;
//
//public class AssignmentUI {
//
//	protected Shell shell;
//	private Assignment assignment;
//	private ArrayList<Question> questions;
//	private Student student;
//
//
//	/**
//	 * Launch the application.
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		try {
//			Assignment a = new Assignment("CSCC07",7);
//			Student student = new Student("12432423432","Jhon","Smith","CSCC01","TUT01");
//			AssignmentUI window = new AssignmentUI(a,student);
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
//	public AssignmentUI(Assignment ass,Student stud) {
//	    // -1 id marks a new assignment not in the database
//	    this.student = stud;
//	    	this.assignment = ass;
//	    this.questions = (ArrayList<Question>)new QuestionAction().getQuestionsWithAnswer(ass.getId());
//	 }
//	/**
//	 * Open the window.
//	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shell.open();
//		shell.layout();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 */
//	protected void createContents() {
//		shell = new Shell();
//		shell.setSize(450, 301);
//		shell.setText("SWT Application");
//		shell.setLayout(null);;
//
//		// setting answer options
//		Group rdgroup = new Group(shell,SWT.NONE);
//		RowLayout rowL = new RowLayout(SWT.VERTICAL);
//		rowL.spacing = 20;
//		rdgroup.setBackground(shell.getBackground());
//		rdgroup.setLayout(rowL);
//		//  buttons
//		Button btnSaveNext = new Button(shell, SWT.NONE);
//		btnSaveNext.setBounds(238, 241, 94, 28);
//		btnSaveNext.setText("Save & Next");
//		btnSaveNext.setEnabled(false);
//
//		Button btnCancle = new Button(shell, SWT.NONE);
//		btnCancle.setBounds(346, 241, 94, 28);
//		btnCancle.setText("Cancle");
//
//
//		Button btnPrev = new Button(shell, SWT.NONE);
//		btnPrev.setBounds(130, 241, 94, 28);
//		btnPrev.setText("prev");
//		btnPrev.setEnabled(false);
//		// question label
//		Label lblNewLabel = new Label(shell, SWT.NONE);
//		lblNewLabel.setBounds(40, 34, 350, 40);
//		lblNewLabel.setText("1. "+this.questions.get(0).getQuestion());
//
//		ArrayList<Integer> answers = new ArrayList<Integer>();
//		ArrayList<Button> rdbts = new ArrayList<Button>();
//		Listener answerListener = new Listener() {
//
//			 public void handleEvent(Event e) {
//				 Button bt = (Button)e.widget;
//				    switch (e.type) {
//			          case SWT.Selection:
//			        	  if(bt.getSelection()) {
//			        		  btnSaveNext.setEnabled(true);
//			        	  }
//
//				    }
//			 }
//		};
//
//
//
//
//		for (String answer:this.questions.get(0).getAnswerList()) {
//			rdbts.add(new Button(rdgroup, SWT.RADIO));
//			rdbts.get(rdbts.size() - 1).setText(answer + String.valueOf(0));
//			rdbts.get(rdbts.size() - 1).addListener(SWT.Selection,answerListener);
//		}
//
//		rdgroup.setBounds(50, 80, 350, (int) (37.5 * this.questions.get(0).getAnswerList().size()) );
//
//
//
//	    Listener prevNext = new Listener() {
//	    		private  int currentAt = 0;
//	        public void handleEvent(Event e) {
//
//	          switch (e.type) {
//	          case SWT.Selection:
//
//	        	  if(e.widget == btnSaveNext) {
//	        		  // get the answer
//	        		  Integer answerInt = 0;
//	        		  for(Button rdb:rdbts) {
//	        			  if(rdb.getSelection()) {
//	        				  break;
//	        			  }
//	        			  answerInt += 1;
//	        		  }
//	        		  // save the answer or change the existing answer
//	        		  try {
//	        			  answers.get(currentAt);
//	        			  answers.set(currentAt, answerInt);
//	        		  }catch(IndexOutOfBoundsException ex) {
//	        			  answers.add(answerInt);
//	        		  }
//	        		  if(currentAt == questions.size() - 1) {
//	        			  // close uI and submit the answers
//		        		  shell.dispose();
//		        		 	TreeMap<Question,String> qa = new TreeMap<Question,String>();
//			        	  	for(int i=0;i < questions.size();i++) {
//			        	  		Question q = questions.get(i);
//			        	  		String a = String.valueOf(answers.get(i));
//			        	  		qa.put(q, a);
//
//			        	  	}
//			        	  	StudentAction.addAnswerToAssignment(student, assignment, qa);
//		        	  }else {
//
//
//
//		        		  currentAt += 1;
//		        		  // trying to see if we have next answer
//		        		  try {
//		        			  answers.get(currentAt);
//		        		 }catch(IndexOutOfBoundsException ex) {
//		        			  btnSaveNext.setEnabled(false);
//		        		 }
//		        		  // rendering the next question and answers
//		        		  lblNewLabel.setText( String.valueOf(currentAt+1)+". " + questions.get(currentAt).getQuestion());
//		        		  for (Button each:rdbts) {
//		        			  each.dispose();
//		        		  }
//		        		  rdbts.clear();
//			        	  for (String answer:questions.get(currentAt).getAnswerList()) {
//			      			rdbts.add(new Button(rdgroup, SWT.RADIO));
//
//			      			rdbts.get(rdbts.size() - 1).setText(answer +String.valueOf(currentAt));
//			      			rdbts.get(rdbts.size() - 1).addListener(SWT.Selection,answerListener);
//			      	  }
//			        	  // setting anwser for rendered question if we have
//			        	  try {
//			        		  int answer =  answers.get(currentAt);
//			        		  rdbts.get(answer).setSelection(true);
//			        	  }catch(IndexOutOfBoundsException ex) {
//
//			        	  }
//			        	  // setting rendering range
//			        	  rdgroup.setBounds(50, 80, 350, (int) (37.5 * questions.get(currentAt).getAnswerList().size()) );
//			        	  rdgroup.layout();
//			        	  btnPrev.setEnabled(true);
//			        	  // see if we have reach the end
//			          if(currentAt == questions.size() - 1) {
//			        		  btnSaveNext.setText("Submit");
//			        	  }
//		        	  }
//
//
//	        	  }else if(e.widget == btnPrev) {
//	        		  if(currentAt == questions.size() - 1 ) {
//	        			  btnSaveNext.setText("Save & Next");
//		        	  }
//	        		  currentAt -= 1;
//
//
//	        		  for (Button each:rdbts) {
//	        			  each.dispose();
//	        		  }
//	        		  rdbts.clear();
//	        		  lblNewLabel.setText(String.valueOf(currentAt+1)+". "+questions.get(currentAt).getQuestion());
//		        	  for (String answer:questions.get(currentAt).getAnswerList()) {
//		      			rdbts.add(new Button(rdgroup, SWT.RADIO));
//
//		      			rdbts.get(rdbts.size() - 1).setText(answer + String.valueOf(currentAt));
//		      			rdbts.get(rdbts.size() - 1).addListener(SWT.Selection,answerListener);
//		      	  }
//		        	  // trying to see if we have prev answer, and set it
//	        		  try {
//	        			  int answer = answers.get(currentAt);
//	        			  rdbts.get(answer).setSelection(true);
//	        			  btnSaveNext.setEnabled(true);
//	        		 }catch(IndexOutOfBoundsException ex) {
//	        		 }
//		        	  rdgroup.setBounds(50, 80, 350, (int) (37.5*questions.get(currentAt).getAnswerList().size()) );
//		         	rdgroup.layout();
//		          if(currentAt == 0) {
//		        	  	btnPrev.setEnabled(false);
//
//		        	  }
//	        	  }
//
//	          break;
//	          }
//	        }
//	      };
//
//	      btnSaveNext.addListener(SWT.Selection, prevNext);
//	      btnPrev.addListener(SWT.Selection, prevNext);
//
//	    btnCancle.addListener(SWT.Selection, new Listener() {
//	        public void handleEvent(Event e) {
//	          switch (e.type) {
//	          case SWT.Selection:
//	        	  	shell.dispose();
//	            break;
//	          }
//	        }
//	      });
//
//
//	}
//
//
//
//
//}
