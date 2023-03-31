namespace lab1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.instructorsDataGridView1 = new System.Windows.Forms.DataGridView();
            this.vehiclesDataGridView = new System.Windows.Forms.DataGridView();
            this.instructorCNPTextBox = new System.Windows.Forms.TextBox();
            this.carPlateTextBox = new System.Windows.Forms.TextBox();
            this.carChasisTextBox = new System.Windows.Forms.TextBox();
            this.addCarButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.vehiclesTabControl = new System.Windows.Forms.TabControl();
            this.addTab = new System.Windows.Forms.TabPage();
            this.updateTab = new System.Windows.Forms.TabPage();
            this.instructorCNPUpdateTextField = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.carPlateUpdateTextField = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.carChasisUpdateTextField = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.updateButton = new System.Windows.Forms.Button();
            this.carIdUpdateTextField = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.instructorsDataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.vehiclesDataGridView)).BeginInit();
            this.vehiclesTabControl.SuspendLayout();
            this.addTab.SuspendLayout();
            this.updateTab.SuspendLayout();
            this.SuspendLayout();
            // 
            // instructorsDataGridView1
            // 
            this.instructorsDataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.instructorsDataGridView1.Location = new System.Drawing.Point(12, 12);
            this.instructorsDataGridView1.Name = "instructorsDataGridView1";
            this.instructorsDataGridView1.RowTemplate.Height = 25;
            this.instructorsDataGridView1.Size = new System.Drawing.Size(776, 150);
            this.instructorsDataGridView1.TabIndex = 0;
            this.instructorsDataGridView1.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.instructorsDataGridView1_CellClick);
            // 
            // vehiclesDataGridView
            // 
            this.vehiclesDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.vehiclesDataGridView.Location = new System.Drawing.Point(12, 184);
            this.vehiclesDataGridView.Name = "vehiclesDataGridView";
            this.vehiclesDataGridView.RowTemplate.Height = 25;
            this.vehiclesDataGridView.Size = new System.Drawing.Size(776, 150);
            this.vehiclesDataGridView.TabIndex = 1;
            this.vehiclesDataGridView.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.vehiclesDataGridView_CellClick);
            // 
            // instructorCNPTextBox
            // 
            this.instructorCNPTextBox.Location = new System.Drawing.Point(131, 36);
            this.instructorCNPTextBox.Name = "instructorCNPTextBox";
            this.instructorCNPTextBox.Size = new System.Drawing.Size(177, 23);
            this.instructorCNPTextBox.TabIndex = 2;
            // 
            // carPlateTextBox
            // 
            this.carPlateTextBox.Location = new System.Drawing.Point(131, 94);
            this.carPlateTextBox.Name = "carPlateTextBox";
            this.carPlateTextBox.Size = new System.Drawing.Size(177, 23);
            this.carPlateTextBox.TabIndex = 3;
            // 
            // carChasisTextBox
            // 
            this.carChasisTextBox.Location = new System.Drawing.Point(131, 65);
            this.carChasisTextBox.Name = "carChasisTextBox";
            this.carChasisTextBox.Size = new System.Drawing.Size(177, 23);
            this.carChasisTextBox.TabIndex = 4;
            // 
            // addCarButton
            // 
            this.addCarButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.addCarButton.Location = new System.Drawing.Point(131, 139);
            this.addCarButton.Name = "addCarButton";
            this.addCarButton.Size = new System.Drawing.Size(177, 31);
            this.addCarButton.TabIndex = 5;
            this.addCarButton.Text = "Add";
            this.addCarButton.UseVisualStyleBackColor = true;
            this.addCarButton.Click += new System.EventHandler(this.addCarButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(36, 39);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 15);
            this.label1.TabIndex = 6;
            this.label1.Text = "Instructor CNP";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(36, 65);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(62, 15);
            this.label2.TabIndex = 7;
            this.label2.Text = "Car Chasis";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(36, 97);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 15);
            this.label3.TabIndex = 8;
            this.label3.Text = "Car Plate";
            // 
            // vehiclesTabControl
            // 
            this.vehiclesTabControl.Controls.Add(this.addTab);
            this.vehiclesTabControl.Controls.Add(this.updateTab);
            this.vehiclesTabControl.Location = new System.Drawing.Point(2, 340);
            this.vehiclesTabControl.Name = "vehiclesTabControl";
            this.vehiclesTabControl.SelectedIndex = 0;
            this.vehiclesTabControl.Size = new System.Drawing.Size(799, 228);
            this.vehiclesTabControl.TabIndex = 9;
            // 
            // addTab
            // 
            this.addTab.Controls.Add(this.instructorCNPTextBox);
            this.addTab.Controls.Add(this.label3);
            this.addTab.Controls.Add(this.carPlateTextBox);
            this.addTab.Controls.Add(this.label2);
            this.addTab.Controls.Add(this.carChasisTextBox);
            this.addTab.Controls.Add(this.label1);
            this.addTab.Controls.Add(this.addCarButton);
            this.addTab.Location = new System.Drawing.Point(4, 24);
            this.addTab.Name = "addTab";
            this.addTab.Padding = new System.Windows.Forms.Padding(3);
            this.addTab.Size = new System.Drawing.Size(791, 200);
            this.addTab.TabIndex = 0;
            this.addTab.Text = "Add";
            this.addTab.UseVisualStyleBackColor = true;
            // 
            // updateTab
            // 
            this.updateTab.Controls.Add(this.carIdUpdateTextField);
            this.updateTab.Controls.Add(this.label7);
            this.updateTab.Controls.Add(this.instructorCNPUpdateTextField);
            this.updateTab.Controls.Add(this.label4);
            this.updateTab.Controls.Add(this.carPlateUpdateTextField);
            this.updateTab.Controls.Add(this.label5);
            this.updateTab.Controls.Add(this.carChasisUpdateTextField);
            this.updateTab.Controls.Add(this.label6);
            this.updateTab.Controls.Add(this.updateButton);
            this.updateTab.Location = new System.Drawing.Point(4, 24);
            this.updateTab.Name = "updateTab";
            this.updateTab.Padding = new System.Windows.Forms.Padding(3);
            this.updateTab.Size = new System.Drawing.Size(791, 200);
            this.updateTab.TabIndex = 1;
            this.updateTab.Text = "Update";
            this.updateTab.UseVisualStyleBackColor = true;
            // 
            // instructorCNPUpdateTextField
            // 
            this.instructorCNPUpdateTextField.Location = new System.Drawing.Point(120, 59);
            this.instructorCNPUpdateTextField.Name = "instructorCNPUpdateTextField";
            this.instructorCNPUpdateTextField.Size = new System.Drawing.Size(177, 23);
            this.instructorCNPUpdateTextField.TabIndex = 9;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(25, 120);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(54, 15);
            this.label4.TabIndex = 15;
            this.label4.Text = "Car Plate";
            // 
            // carPlateUpdateTextField
            // 
            this.carPlateUpdateTextField.Location = new System.Drawing.Point(120, 117);
            this.carPlateUpdateTextField.Name = "carPlateUpdateTextField";
            this.carPlateUpdateTextField.Size = new System.Drawing.Size(177, 23);
            this.carPlateUpdateTextField.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(25, 88);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(62, 15);
            this.label5.TabIndex = 14;
            this.label5.Text = "Car Chasis";
            // 
            // carChasisUpdateTextField
            // 
            this.carChasisUpdateTextField.Location = new System.Drawing.Point(120, 88);
            this.carChasisUpdateTextField.Name = "carChasisUpdateTextField";
            this.carChasisUpdateTextField.Size = new System.Drawing.Size(177, 23);
            this.carChasisUpdateTextField.TabIndex = 11;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(25, 62);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(85, 15);
            this.label6.TabIndex = 13;
            this.label6.Text = "Instructor CNP";
            // 
            // updateButton
            // 
            this.updateButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.updateButton.Location = new System.Drawing.Point(120, 162);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(177, 31);
            this.updateButton.TabIndex = 12;
            this.updateButton.Text = "Update";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // carIdUpdateTextField
            // 
            this.carIdUpdateTextField.Location = new System.Drawing.Point(120, 30);
            this.carIdUpdateTextField.Name = "carIdUpdateTextField";
            this.carIdUpdateTextField.Size = new System.Drawing.Size(177, 23);
            this.carIdUpdateTextField.TabIndex = 16;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(25, 33);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(17, 15);
            this.label7.TabIndex = 17;
            this.label7.Text = "Id";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 569);
            this.Controls.Add(this.vehiclesTabControl);
            this.Controls.Add(this.vehiclesDataGridView);
            this.Controls.Add(this.instructorsDataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.instructorsDataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.vehiclesDataGridView)).EndInit();
            this.vehiclesTabControl.ResumeLayout(false);
            this.addTab.ResumeLayout(false);
            this.addTab.PerformLayout();
            this.updateTab.ResumeLayout(false);
            this.updateTab.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private DataGridView instructorsDataGridView1;
        private DataGridView vehiclesDataGridView;
        private TextBox instructorCNPTextBox;
        private TextBox carPlateTextBox;
        private TextBox carChasisTextBox;
        private Button addCarButton;
        private Label label1;
        private Label label2;
        private Label label3;
        private TabControl vehiclesTabControl;
        private TabPage addTab;
        private TabPage updateTab;
        private TextBox carIdUpdateTextField;
        private Label label7;
        private TextBox instructorCNPUpdateTextField;
        private Label label4;
        private TextBox carPlateUpdateTextField;
        private Label label5;
        private TextBox carChasisUpdateTextField;
        private Label label6;
        private Button updateButton;
    }
}