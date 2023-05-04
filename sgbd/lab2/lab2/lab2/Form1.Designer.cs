namespace lab2
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
            this.dataGridViewParentTable = new System.Windows.Forms.DataGridView();
            this.dataGridViewChildTable = new System.Windows.Forms.DataGridView();
            this.buttonUpdateDatabase = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParentTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChildTable)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewParentTable
            // 
            this.dataGridViewParentTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewParentTable.Location = new System.Drawing.Point(0, 1);
            this.dataGridViewParentTable.Name = "dataGridViewParentTable";
            this.dataGridViewParentTable.RowTemplate.Height = 25;
            this.dataGridViewParentTable.Size = new System.Drawing.Size(800, 161);
            this.dataGridViewParentTable.TabIndex = 0;
            // 
            // dataGridViewChildTable
            // 
            this.dataGridViewChildTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewChildTable.Location = new System.Drawing.Point(0, 215);
            this.dataGridViewChildTable.Name = "dataGridViewChildTable";
            this.dataGridViewChildTable.RowTemplate.Height = 25;
            this.dataGridViewChildTable.Size = new System.Drawing.Size(800, 161);
            this.dataGridViewChildTable.TabIndex = 1;
            // 
            // buttonUpdateDatabase
            // 
            this.buttonUpdateDatabase.Location = new System.Drawing.Point(628, 406);
            this.buttonUpdateDatabase.Name = "buttonUpdateDatabase";
            this.buttonUpdateDatabase.Size = new System.Drawing.Size(141, 23);
            this.buttonUpdateDatabase.TabIndex = 2;
            this.buttonUpdateDatabase.Text = "Update Database";
            this.buttonUpdateDatabase.UseVisualStyleBackColor = true;
            this.buttonUpdateDatabase.Click += new System.EventHandler(this.buttonUpdateDatabase_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.buttonUpdateDatabase);
            this.Controls.Add(this.dataGridViewChildTable);
            this.Controls.Add(this.dataGridViewParentTable);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParentTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChildTable)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DataGridView dataGridViewParentTable;
        private DataGridView dataGridViewChildTable;
        private Button buttonUpdateDatabase;
    }
}