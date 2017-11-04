package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import zodiac.action.QuestionAction;
import zodiac.definition.coursework.*;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class AssignmentUI {

	protected Shell shell;
	private Assignment assignment;
	private ArrayList<Question> questions;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AssignmentUI window = new AssignmentUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 301);
		shell.setText("SWT Application");
		shell.setLayout(null);
		int hardCodeId = 7;
		List<Question> QuestionList = new QuestionAction().getQid(hardCodeId);
	
		// setting answer options 
		Group rdgroup = new Group(shell,SWT.NONE);
		RowLayout rowL = new RowLayout(SWT.VERTICAL);
		rowL.spacing = 20;
		rdgroup.setBackground(shell.getBackground());
		rdgroup.setLayout(rowL);
		ArrayList<Button> rdbts = new ArrayList<Button>();
		for (String answer:QuestionList.get(0).getAnswerList()) {
			rdbts.add(new Button(rdgroup, SWT.RADIO));
			rdbts.get(rdbts.size() - 1).setText(answer + String.valueOf(0));
		}
		
		rdgroup.setBounds(50, 80, 350, 150);
		// save and cancel button
		Button btnSaveNext = new Button(shell, SWT.NONE);
		btnSaveNext.setBounds(238, 241, 94, 28);
		btnSaveNext.setText("Save & Next");
		
		Button btnCancle = new Button(shell, SWT.NONE);
		btnCancle.setBounds(346, 241, 94, 28);
		btnCancle.setText("Cancle");
		
		Button btnPrev = new Button(shell, SWT.NONE);
		btnPrev.setBounds(130, 241, 94, 28);
		btnPrev.setText("prev");
		btnPrev.setEnabled(false);
	
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(40, 34, 350, 40);
		lblNewLabel.setText(QuestionList.get(0).getQuestion());
		
	     
	    Listener prevNext = new Listener() {
	    		private  int currentAt = 0;
	        public void handleEvent(Event e) {
	        	 
	          switch (e.type) {
	          case SWT.Selection:
	        	 
	        	  if(e.widget == btnSaveNext) {
	        		  if(currentAt == QuestionList.size() - 1) {
		        		  shell.dispose();
		        	  }else {
		        		  currentAt += 1;
		        		  lblNewLabel.setText(QuestionList.get(currentAt).getQuestion());
		        		  for (Button each:rdbts) {
		        			  each.dispose();
		        		  }
		        		  rdbts.clear();
			        	  for (String answer:QuestionList.get(currentAt).getAnswerList()) {
			      			rdbts.add(new Button(rdgroup, SWT.RADIO));
			      		
			      			rdbts.get(rdbts.size() - 1).setText(answer +String.valueOf(currentAt));
			      	  }
			        	  rdgroup.layout();
			        	  btnPrev.setEnabled(true);
			          if(currentAt == QuestionList.size() - 1) {
			        		  btnSaveNext.setText("Complete");
			        	  }
		        	  }
		        	  
		        	
	        	  }else if(e.widget == btnPrev) {
	        		  if(currentAt == QuestionList.size() - 1 ) {
	        			  btnSaveNext.setText("Save & Next");
		        	  }
	        		  currentAt -= 1;
	        		  for (Button each:rdbts) {
	        			  each.dispose();
	        		  }
	        		  rdbts.clear();
	        		  lblNewLabel.setText(QuestionList.get(currentAt).getQuestion());
		        	  for (String answer:QuestionList.get(currentAt).getAnswerList()) {
		      			rdbts.add(new Button(rdgroup, SWT.RADIO));
		      			
		      			rdbts.get(rdbts.size() - 1).setText(answer + String.valueOf(currentAt));
		      	  }
		        	 
		         	rdgroup.layout();
		          if(currentAt == 0) {
		        	  	btnPrev.setEnabled(false);
		 
		        	  }
	        	  }
	        	 
	          break;
	          }
	        }
	      };
	   
	      btnSaveNext.addListener(SWT.Selection, prevNext);  
	      btnPrev.addListener(SWT.Selection, prevNext);
	      
	    btnCancle.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	        	  	shell.dispose();
	            break;
	          }
	        }
	      });
	  
		
	}

	
	
	
}
