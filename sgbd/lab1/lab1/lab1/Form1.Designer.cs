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
            this.vehiclesDataGridView.Location = new System.Drawing.Point(12, 227);
            this.vehiclesDataGridView.Name = "vehiclesDataGridView";
            this.vehiclesDataGridView.RowTemplate.Height = 25;
            this.vehiclesDataGridView.Size = new System.Drawing.Size(776, 150);
            this.vehiclesDataGridView.TabIndex = 1;
            this.vehiclesDataGridView.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.vehiclesDataGridView_CellClick);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 569);
            this.Controls.Add(this.vehiclesDataGridView);
            this.Controls.Add(this.instructorsDataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.instructorsDataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.vehiclesDataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DataGridView instructorsDataGridView1;
        private DataGridView vehiclesDataGridView;
    }
}