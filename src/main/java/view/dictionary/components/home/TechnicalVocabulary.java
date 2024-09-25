package view.dictionary.form;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;


public class MainSearch extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4246021878929953973L;
	
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
	
	public MainSearch() {
        initComponents();
        init();
    }

    private void init() {
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");

        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

        testData();
    }

    private void testData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addRow(new Object[]{false, 1, "Hannah Scott", "Washington, D.C.", "20-August-2024", "$1,750", "Business Analyst", "Analytical thinker with experience in business process improvement."});
        model.addRow(new Object[]{false, 2, "Samantha Smith", "New York City", "15-May-2024", "$1,200", "Marketing Manager", "Experienced marketing professional with a focus on digital advertising."});
        model.addRow(new Object[]{false, 3, "John Johnson", "Los Angeles", "20-May-2024", "$1,500", "Software Engineer", "Skilled developer proficient in Java, Python, and JavaScript."});
        model.addRow(new Object[]{false, 4, "Emily Brown", "Chicago", "25-May-2024", "$1,300", "Graphic Designer", "Creative designer with expertise in Adobe Creative Suite."});
        model.addRow(new Object[]{false, 5, "Michael Davis", "San Francisco", "30-May-2024", "$1,800", "Financial Analyst", "Analytical thinker with a background in financial modeling and forecasting."});
        model.addRow(new Object[]{false, 6, "Jessica Miller", "Seattle", "5-June-2024", "$1,600", "HR Manager", "Human resources professional specializing in recruitment and employee relations."});
        model.addRow(new Object[]{false, 7, "David Martinez", "Miami", "10-June-2024", "$1,700", "Sales Representative", "Proven track record in sales and client relationship management."});
        model.addRow(new Object[]{false, 8, "Sarah Thompson", "Boston", "15-June-2024", "$1,400", "Content Writer", "Versatile writer capable of producing engaging content across various platforms."});
        model.addRow(new Object[]{false, 9, "Daniel Wilson", "Austin", "20-June-2024", "$1,550", "UX/UI Designer", "Design thinker focused on creating intuitive user experiences."});
        model.addRow(new Object[]{false, 10, "Rachel Taylor", "Denver", "25-June-2024", "$1,350", "Accountant", "Detail-oriented accountant with expertise in financial reporting."});
        model.addRow(new Object[]{false, 11, "Ryan Anderson", "Portland", "30-June-2024", "$1,900", "Project Manager", "Organized leader skilled in managing cross-functional teams."});
        model.addRow(new Object[]{false, 12, "Lauren Hernandez", "Phoenix", "5-July-2024", "$1,750", "Marketing Coordinator", "Marketing professional with experience in campaign management and analysis."});
        model.addRow(new Object[]{false, 13, "Kevin Garcia", "Atlanta", "10-July-2024", "$1,650", "Software Developer", "Full-stack developer proficient in front-end and back-end technologies."});
        model.addRow(new Object[]{false, 14, "Amanda Martinez", "Houston", "15-July-2024", "$1,300", "Customer Service Representative", "Dedicated customer service professional committed to resolving issues."});
        model.addRow(new Object[]{false, 15, "Erica Robinson", "Philadelphia", "20-July-2024", "$1,600", "Data Analyst", "Analytical thinker with expertise in data visualization and statistical analysis."});
        model.addRow(new Object[]{false, 16, "Matthew Walker", "Dallas", "25-July-2024", "$1,850", "Operations Manager", "Efficient manager with experience in optimizing operational processes."});
        model.addRow(new Object[]{false, 17, "Olivia Lewis", "Detroit", "30-July-2024", "$1,400", "Social Media Manager", "Strategic thinker with a passion for creating engaging social media content."});
        model.addRow(new Object[]{false, 18, "Nathan King", "Minneapolis", "5-August-2024", "$1,700", "Web Developer", "Skilled web developer with expertise in HTML, CSS, and JavaScript frameworks."});
        model.addRow(new Object[]{false, 19, "Maria Perez", "Orlando", "10-August-2024", "$1,550", "Digital Marketing Specialist", "Experienced marketer focused on digital advertising and SEO strategies."});
        model.addRow(new Object[]{false, 20, "Justin White", "San Diego", "15-August-2024", "$1,450", "Financial Planner", "Certified financial planner with a client-centered approach."});
        

    }
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new JSeparator();

        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SELECT", "#", "Từ vựng", "Phiên âm", "Từ loại" , "Nghĩa", "Mô tả", "Ví dụ"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4232228108642530784L;
			Class<?>[] types = new Class<?>[] {
			    Boolean.class, 
			    Object.class,  
			    Object.class, 
			    Object.class,  
			    Object.class,  
			    Object.class,  
			    Object.class,  
			    Object.class   
			};

			boolean[] canEdit = new boolean[] {
			    true, 
			    false, 
			    false, 
			    true, 
			    false, 
			    false, 
			    false, 
			    false
			};

			@Override
			public Class<?> getColumnClass(int columnIndex) {
			    return types[columnIndex];
			}

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(80);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setPreferredWidth(50);
            table.getColumnModel().getColumn(6).setPreferredWidth(150);
            table.getColumnModel().getColumn(7).setPreferredWidth(150);
        }

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

    }

}
