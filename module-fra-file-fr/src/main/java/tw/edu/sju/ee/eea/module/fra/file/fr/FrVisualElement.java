/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.sju.ee.eea.module.fra.file.fr;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.jfree.chart.ChartPanel;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import tw.edu.sju.ee.eea.ui.workspace.plot.BodePlot;

@MultiViewElement.Registration(
        displayName = "#LBL_Fr_VISUAL",
        iconBase = "tw/edu/sju/ee/eea/module/fra/file/fr/fr.png",
        mimeType = "application/x-plot",
        persistenceType = TopComponent.PERSISTENCE_NEVER,
        preferredID = "FrVisual",
        position = 2000
)
@Messages({
    "LBL_Fr_VISUAL=Chart",
    "LBL_Fr_VISUAL_chart.title=Frequency Response"
})
public final class FrVisualElement extends JPanel implements MultiViewElement {

    @Messages({
        "CMB_show_tip=Select Shows",
        "CMB_show_both=Both",
        "CMB_show_magnitude=Magnitude",
        "CMB_show_phase=Phase"
    })
    private class FrVisualToolBar extends JToolBar {

        private JComboBox cmbShowData = new JComboBox();

        public FrVisualToolBar() {
            cmbShowData.setToolTipText(Bundle.CMB_show_tip());
            cmbShowData.setModel(new DefaultComboBoxModel(
                    new String[]{Bundle.CMB_show_both(), Bundle.CMB_show_magnitude(), Bundle.CMB_show_phase()}));
            cmbShowData.setMaximumSize(new Dimension(80, 21));
            cmbShowData.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ((ChartPanel) chartPanel).setChart(createChart());
                }
            });
            this.setFloatable(false);
            this.addSeparator();
            this.add(cmbShowData);
        }

        private BodePlot createChart() {
            BodePlot bodePlot = new BodePlot(Bundle.LBL_Fr_VISUAL_chart_title());
            bodePlot.setData(obj.getFile().getGain(), cmbShowData.getSelectedIndex());
            return bodePlot;
        }
    }

    private FrDataObject obj;
    private FrVisualToolBar toolbar = new FrVisualToolBar();
    private transient MultiViewElementCallback callback;

//    private ChartPanel chartPanel;
    public FrVisualElement(Lookup lkp) {
        obj = lkp.lookup(FrDataObject.class);
        assert obj != null;
        initComponents();
    }

    @Override
    public String getName() {
        return "FrVisualElement";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartPanel = new ChartPanel(toolbar.createChart());

        setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar;
    }

    @Override
    public Action[] getActions() {
        return new Action[0];
    }

    @Override
    public Lookup getLookup() {
        return obj.getLookup();
    }

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    @Override
    public void componentShowing() {
    }

    @Override
    public void componentHidden() {
    }

    @Override
    public void componentActivated() {
    }

    @Override
    public void componentDeactivated() {
    }

    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
        callback.getTopComponent().setDisplayName(obj.getPrimaryFile().getName());
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }

}
