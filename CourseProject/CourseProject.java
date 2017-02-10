package week7;


import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class CourseProject extends JPanel
{
	    public CourseProject()
	    {
	        final JTabbedPane tabbedPane = new JTabbedPane();
	        final Pool JTB1 = new Pool();
	        final HotTub JTB2 = new HotTub();
	        final DistanceConverter JTB3 = new DistanceConverter();
	        final TempConverter JTB4 = new TempConverter();
	        
	       //final JPanel JTB2 = new OfficeAreaCalculator();

	        tabbedPane.add("Pool", JTB1);
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        tabbedPane.add("HotTub", JTB2);
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        tabbedPane.add("DistanceConverter", JTB3);
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        tabbedPane.add("TempConverter", JTB4);
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

	        add(tabbedPane);
	        
	    }
	    // Create JTabbedPane
	    private static void createAndShowGUI()
	    {
	        // Create and set up the window.
	        JFrame frame = new JFrame("JTabbedPane");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(new CourseProject(), BorderLayout.CENTER);
	        frame.pack();
	        frame.setVisible(true);
	    }


	    public static void main(String[] args)
	    {
	        SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                createAndShowGUI();
	            }
	        });
	    }
	}
