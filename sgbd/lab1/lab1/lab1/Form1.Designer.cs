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
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.textBox3 = new System.Windows.Forms.TextBox();
            this.addCarButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.instructorsDataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.vehiclesDataGridView)).BeginInit();
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
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(107, 352);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(177, 23);
            this.textBox1.TabIndex = 2;
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(107, 410);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(177, 23);
            this.textBox2.TabIndex = 3;
            // 
            // textBox3
            // 
            this.textBox3.Location = new System.Drawing.Point(107, 381);
            this.textBox3.Name = "textBox3";
            this.textBox3.Size = new System.Drawing.Size(177, 23);
            this.textBox3.TabIndex = 4;
            // 
            // addCarButton
            // 
            this.addCarButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.addCarButton.Location = new System.Drawing.Point(107, 455);
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
            this.label1.Location = new System.Drawing.Point(12, 355);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 15);
            this.label1.TabIndex = 6;
            this.label1.Text = "Instructor CNP";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 381);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(62, 15);
            this.label2.TabIndex = 7;
            this.label2.Text = "Car Chasis";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 413);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 15);
            this.label3.TabIndex = 8;
            this.label3.Text = "Car Plate";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 569);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.addCarButton);
            this.Controls.Add(this.textBox3);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.vehiclesDataGridView);
            this.Controls.Add(this.instructorsDataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.instructorsDataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.vehiclesDataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DataGridView instructorsDataGridView1;
        private DataGridView vehiclesDataGridView;
        private TextBox textBox1;
        private TextBox textBox2;
        private TextBox textBox3;
        private Button addCarButton;
        private Label label1;
        private Label label2;
        private Label label3;
    }
}