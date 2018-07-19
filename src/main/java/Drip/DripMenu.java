package Drip;

import util.DoubleRange;
import util.RangeSlider;
import util.Vector2f;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;

public class DripMenu extends JPanel
{
    private DripPanel dripPanel;
    private DripInfoManager dripInfoManager;

    public DripMenu(DripPanel panel)
    {
        super();
        setBackground(new Color(255, 255, 255, 225));
        dripInfoManager = panel.getDripInfoManager();
        this.dripPanel = panel;

        setupUI();
    }

    private void setupUI()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSizeRange();
        addLateralVelocityRange();
        addVerticalVelocityRange();
        addNumDripsSpinner();
        addGravitySpinner();
        addLifetimeSpinner();
        addMaxElementsSpinner();
        addColorSlider();
    }

    // Helper method to prevent reuse of this code.
    private Label getBoldLabelTemplate(String message)
    {
        Label label = new Label(message);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignment(Label.CENTER);

        return label;
    }

    // Helper method to prevent reuse of this code.
    private static JSpinner getSpinnerTemplate()
    {
        final JSpinner spinner = new JSpinner();
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComponent comp = spinner.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        return spinner;
    }

    private void addSizeRange()
    {
        add(getBoldLabelTemplate("Set Size Range:"));

        RangeSlider slider = new RangeSlider(1, 25);
        slider.setValue(1);
        slider.setUpperValue(10);

        double difference = slider.getUpperValue() - slider.getValue();
        dripInfoManager.setSize(new DoubleRange(slider.getValue(), difference));
        slider.addChangeListener(e -> {
            double differenceInternal = slider.getUpperValue() - slider.getValue();
            dripInfoManager.setSize(new DoubleRange(slider.getValue(), differenceInternal));
        });
        add(slider);
    }

    private void addLateralVelocityRange()
    {
        add(getBoldLabelTemplate("Set Horizontal Velocity Range:"));

        RangeSlider slider = new RangeSlider(-10, 10);
        slider.setValue(-5);
        slider.setUpperValue(5);

        double difference = slider.getUpperValue() - slider.getValue();
        dripInfoManager.setLateralVelocity(new DoubleRange(slider.getValue(), difference));
        slider.addChangeListener(e -> {
            double differenceInternal = slider.getUpperValue() - slider.getValue();
            dripInfoManager.setLateralVelocity(new DoubleRange(slider.getValue(), differenceInternal));
        });
        add(slider);
    }

    private void addVerticalVelocityRange()
    {
        add(getBoldLabelTemplate("Set Vertical Velocity Range:"));

        RangeSlider slider = new RangeSlider(-5, 5);
        slider.setValue(0);
        slider.setUpperValue(5);

        double difference = slider.getUpperValue() - slider.getValue();
        dripInfoManager.setVerticalVelocity(new DoubleRange(slider.getValue(), difference));
        slider.addChangeListener(e -> {
            double differenceInternal = slider.getUpperValue() - slider.getValue();
            dripInfoManager.setVerticalVelocity(new DoubleRange(slider.getValue(), differenceInternal));
        });
        add(slider);
    }

    private void addNumDripsSpinner()
    {
        add(getBoldLabelTemplate("Set max number of drips:"));

        final JSpinner spinner = getSpinnerTemplate();
        SpinnerNumberModel model = new SpinnerNumberModel(3, 0, 100, 1d);
        spinner.setModel(model);

        dripInfoManager.setNumberOfDrips(new DoubleRange(0, (double)spinner.getValue()));
        spinner.addChangeListener(e -> dripInfoManager.setNumberOfDrips(new DoubleRange(0, (double)spinner.getValue())));
        add(spinner);
    }

    private void addGravitySpinner()
    {
        add(getBoldLabelTemplate("Set gravity:"));

        final JSpinner spinner = getSpinnerTemplate();
        SpinnerNumberModel model = new SpinnerNumberModel(3, -8, 8, 1d);
        spinner.setModel(model);

        dripInfoManager.setGravity(new Vector2f(0, (double)spinner.getValue() / 10));
        spinner.addChangeListener(e -> dripInfoManager.setGravity(new Vector2f(0, (double)spinner.getValue() / 10)));
        add(spinner);
    }

    private void addLifetimeSpinner()
    {
        add(getBoldLabelTemplate("Set max lifetime (sec):"));

        final JSpinner spinner = getSpinnerTemplate();
        SpinnerNumberModel model = new SpinnerNumberModel(1, 0, 5, 0.1d);
        spinner.setModel(model);

        dripInfoManager.setLifeTimeMS((int)((double)spinner.getValue()) * 1000);
        spinner.addChangeListener(e -> dripInfoManager.setLifeTimeMS((int)((double)spinner.getValue()) * 1000));
        add(spinner);
    }

    private void addMaxElementsSpinner()
    {
        add(getBoldLabelTemplate("Set max number of drips:"));

        final JSpinner spinner = getSpinnerTemplate();
        SpinnerNumberModel model = new SpinnerNumberModel(3, 0, 100, 1d);
        spinner.setModel(model);

        dripInfoManager.setNumberOfDrips(new DoubleRange(0, (double)spinner.getValue()));
        spinner.addChangeListener(e -> dripInfoManager.setNumberOfDrips(new DoubleRange(0, (double)spinner.getValue())));
        add(spinner);
    }

    private void addColorSlider()
    {
        add(getBoldLabelTemplate("Set color:"));

        add(new Label("Set color for..."));
        JRadioButton backgroundBtn = new JRadioButton("Background");
        JRadioButton dripsBtn = new JRadioButton("Drips");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(dripsBtn);
        btnGroup.add(backgroundBtn);
        dripsBtn.setSelected(true);

        JSlider redSlider = new JSlider(0, 255, 0);
        JSlider blueSlider = new JSlider(0, 255, 0);
        JSlider greenSlider = new JSlider(0, 255, 255);
        JSlider opacitySlider = new JSlider(0, 255, 100);

        redSlider.addChangeListener(e -> {
            if (dripsBtn.isSelected()) {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            } else {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });
        blueSlider.addChangeListener(e -> {
            if (dripsBtn.isSelected()) {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            } else {
                dripPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });
        greenSlider.addChangeListener(e -> {
            if (dripsBtn.isSelected()) {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            } else {
                dripPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });
        opacitySlider.addChangeListener(e -> {
            if (dripsBtn.isSelected()) {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            } else {
                dripPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });

        backgroundBtn.addChangeListener(e -> {
            if (backgroundBtn.isSelected()) {
                dripPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });
        dripsBtn.addChangeListener(e -> {
            if (dripsBtn.isSelected()) {
                dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));
            }
        });
        dripPanel.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue(), opacitySlider.getValue()));

        add(dripsBtn);
        add(backgroundBtn);
        add(new Label("Red:"));
        add(redSlider);
        add(new Label("Blue:"));
        add(blueSlider);
        add(new Label("Green:"));
        add(greenSlider);
        add(new Label("Opacity:"));
        add(opacitySlider);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(360, 600);
    }
}
