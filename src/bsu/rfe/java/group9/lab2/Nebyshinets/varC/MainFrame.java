package bsu.rfe.java.group9.lab2.Nebyshinets.varC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 320;

	private int formula_id = 1;
	private int memory_id = 1;

	private ButtonGroup formula_buttons = new ButtonGroup();
	private Box formula_box;
	private void addFormulaButton(String button_name, final int id){
		JRadioButton button = new JRadioButton(button_name);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.formula_id = id;
			}
		});
		formula_buttons.add(button);
		formula_box.add(button);
	}

	double formula1(double x, double y,  double z){
		return x + y + z;
	}
	double formula2(double x, double y, double z){
		return x*y*z;
	}

	private Box memory_display_box;
	private ButtonGroup choose_memory_buttons = new ButtonGroup();
	private JRadioButton addChooseMemoryButton(int id){
		JRadioButton button = new JRadioButton("0.0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.memory_id = id;
			}
		});
		choose_memory_buttons.add(button);
		memory_display_box.add(button);
		return button;
	}

	public MainFrame() {
		super("Calculation");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);

		Box content_box = Box.createHorizontalBox();
		Box right_panel_box = Box.createVerticalBox();
		Box left_panel_box = Box.createVerticalBox();
		memory_display_box = Box.createVerticalBox();
		Box memory_control_box = Box.createHorizontalBox();
		formula_box = Box.createVerticalBox();
		Box variable_box = Box.createHorizontalBox();
		Box calculate_box = Box.createHorizontalBox();

		addFormulaButton("formula 1", 1);
		addFormulaButton("formula 2", 2);
		formula_buttons.setSelected(formula_buttons.getElements().nextElement().getModel(), true);

		JTextField x_field = new JTextField("0", 5);
		x_field.setMaximumSize(x_field.getPreferredSize());
		JTextField y_field = new JTextField("0", 5);
		y_field.setMaximumSize(y_field.getPreferredSize());
		JTextField z_field = new JTextField("0", 5);
		z_field.setMaximumSize(z_field.getPreferredSize());
		JLabel result_field = new JLabel("0.0");
		variable_box.add(Box.createVerticalGlue());
		variable_box.add(x_field);
		variable_box.add(y_field);
		variable_box.add(z_field);
		variable_box.add(result_field);
		//variable_box.add(Box.createVerticalGlue());

		JButton calculate_button = new JButton("Calculate");
		calculate_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					double x = Double.parseDouble(x_field.getText());
					double y = Double.parseDouble(y_field.getText());
					double z = Double.parseDouble(z_field.getText());
					double result = 0;
					if(formula_id == 1){
						result = formula1(x, y, z);
					} else if(formula_id == 2){
						result = formula2(x, y, z);
					}
					result_field.setText(String.valueOf(result));
				} catch(NumberFormatException ex) {
					System.out.println("Are you durak?");
				}
			}
		});
		JButton reset_button = new JButton("Reset");
		reset_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				x_field.setText("0");
				y_field.setText("0");
				z_field.setText("0");
				result_field.setText("0");
			}
		});
		calculate_box.add(calculate_button);
		calculate_box.add(reset_button);

		JRadioButton memory_button_1 = addChooseMemoryButton(1);
		JRadioButton memory_button_2 = addChooseMemoryButton(2);
		JRadioButton memory_button_3 = addChooseMemoryButton(3);
		choose_memory_buttons.setSelected(choose_memory_buttons.getElements().nextElement().getModel(), true);

		JButton memory_add = new JButton("M+");
		memory_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double memory = Double.parseDouble(memory_button_1.getText());
				memory += Double.parseDouble(result_field.getText());
				memory_button_1.setText(String.valueOf(memory));
			}
		});
		JButton memory_clear = new JButton("MC");
		memory_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memory_button_1.setText("0.0");
			}
		});
		memory_control_box.add(memory_add);
		memory_control_box.add(memory_clear);


		left_panel_box.add(memory_display_box);
		left_panel_box.add(memory_control_box);
		right_panel_box.add(Box.createVerticalGlue());
		right_panel_box.add(formula_box);
		right_panel_box.add(variable_box);
		right_panel_box.add(calculate_box);
		right_panel_box.add(Box.createVerticalGlue());
		content_box.add(left_panel_box);
		content_box.add(right_panel_box);
		getContentPane().add(content_box, BorderLayout.CENTER);
	}


    public static void main(String[] args) {
	    MainFrame frame = new MainFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
    }
}
