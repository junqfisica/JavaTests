package tjunqueira.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4773319286892995766L;
	
	private EventListenerList listenerList = new EventListenerList();
	private JButton addBtn;
	private JButton multiThreadTestBtn;
	private JButton checkTestBtn;
	private JTextField nameField,occupationField;
	private JProgressBar progressBar;
	
	public DetailsPanel() {
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Personal Details"));
		
		JLabel nameLabel = new JLabel("Name: ");
		JLabel occupationLabel = new JLabel("Occupation: ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener((e) -> onClickAddBtn(e));
		
		multiThreadTestBtn = new JButton("Multi-Thread Test");
		multiThreadTestBtn.addActionListener((e) -> onClickmultiThreadTestBtn(e));
		
		checkTestBtn = new JButton("Checksum Test");
		checkTestBtn.addActionListener((e) -> onClickChecksumTest());
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(nameLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(occupationLabel, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(occupationField, gc);
		
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add(addBtn, gc);
		
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(multiThreadTestBtn, gc);
		
		progressBar = new JProgressBar();
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridx = 1;
		gc.gridy = 4;
		add(progressBar, gc);
		
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 5;
		add(checkTestBtn, gc);
		
	}
	
	private void job(Integer value)  {
	   	
	   	Random random = new Random();
	   	
	   	try {
				Thread.sleep(random.nextInt(30000)); // Do sthg...here simulate a job
				System.out.println("Taken value: " + value + "; Thread: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} 
	   }
	
	private void onClickmultiThreadTestBtn(ActionEvent e) {
		
		int jobSize = 200;
		List<Integer> jobList = new ArrayList<Integer>();
		Random random = new Random();
		
		// Create a list of jobs to send in parallel.
		for (int i = 0; i< jobSize; i++) {
      		  jobList.add(i);
      	}
		
		ParallelJobs<Integer> pj = new ParallelJobs<Integer>(jobList,jobList.size(),progressBar);
		
		Runnable theradTask = () -> {
			try {
				pj.startMultiThreadTask((value) -> job(value));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		};
		
		new Thread(theradTask).start();
	}
	
	private void onClickChecksumTest() {
		//String rootDir = "C:\\Users\\junqf\\Desktop\\ReadImagesTest";
		String rootDir = "D:\\ReadImagesTest";
		Runnable theradTask = () -> {
			try {
				List<File> dirFiles = new ArrayList<File>(); 
				dirFiles = ScanFolders.getFolders(rootDir);
	            ParallelJobs<File> pj = new ParallelJobs<File>(dirFiles,1,progressBar);
	            pj.startMultiThreadTask((value) -> getCheckSumToFile(value));
				
			} catch (InterruptedException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		};
		
		new Thread(theradTask).start();
	   
	}
	
	 private void getCheckSumToFile(File folder) {
	        System.out.println("Folder : " + folder.getName() + " Started");
	      //Scan Images.
			try {
				List<File> imageFiles = ScanFolders.getImageFiles(folder.getAbsolutePath());
				CheckSums checksum = new CheckSums();
	            // Add images file to book.
	            for (File imagefile : imageFiles) {
	                String filePath = imagefile.getAbsolutePath();
	                //String  ck = checksum.createHex(ChecksumMethod.SHA1, filePath); // Best way so far.
	                String  ck = checksum.getHash(ChecksumMethod.SHA1, filePath);
	                System.out.println("SHA1: " + ck);
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	       
	        System.out.println("Folder Finished: " + folder.getName());
	    }

	public void onClickAddBtn(ActionEvent e) {
		
		String name = nameField.getText();
		String occupation = occupationField.getText();
		
		String form = "name: " + name + "occupation: " + occupation + "\n"; 
		
		fireDetailEvent(new DetailEvent(this, form));
		
		
	}
	
	private void fireDetailEvent(DetailEvent event ) {
		
		
		for (Object listener : listenerList.getListenerList()) {
			if(listener != DetailListener.class) {
				((DetailListener)listener).detailEventOccurred(event);
			}
		} 
		
	}
	
	public void addDetailListener(DetailListener listener) {
		listenerList.add(DetailListener.class, listener);
	}
	
	public void removeDetailListener(DetailListener listener) {
		listenerList.remove(DetailListener.class, listener);
	}
	
	
}
