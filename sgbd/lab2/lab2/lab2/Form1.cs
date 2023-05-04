using System;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;
using System.Configuration;

namespace lab2
{
    public partial class Form1 : Form
    {
        private DataSet _dataSet = new DataSet();
        private SqlConnection _dbConnection;

        private SqlDataAdapter _dataAdapterParentTable, _dataAdapterChildTable;
        private readonly BindingSource _bindingParentTable = new BindingSource();
        private readonly BindingSource _bindingChildTable = new BindingSource();

        private void InitializeDatabase()
        {
            String connectionString = ConfigurationManager.AppSettings["ConnectionString"];
            _dbConnection = new SqlConnection(connectionString);
            _dataAdapterParentTable = new SqlDataAdapter(ConfigurationManager.AppSettings["SelectParent"], _dbConnection);
            _dataAdapterChildTable = new SqlDataAdapter(ConfigurationManager.AppSettings["SelectChild"], _dbConnection);

            new SqlCommandBuilder(_dataAdapterParentTable);
            new SqlCommandBuilder(_dataAdapterChildTable).GetInsertCommand();

            _dataAdapterParentTable.Fill(_dataSet, ConfigurationManager.AppSettings["ParentTableName"]!);
            _dataAdapterChildTable.Fill(_dataSet, ConfigurationManager.AppSettings["ChildTableName"]!);

            var dataRelation = new DataRelation(
                ConfigurationManager.AppSettings["ForeignKey"],
                _dataSet.Tables[ConfigurationManager.AppSettings["ParentTableName"]].Columns[ConfigurationManager.AppSettings["ParentReferencedKey"]],
                _dataSet.Tables[ConfigurationManager.AppSettings["ChildTableName"]].Columns[ConfigurationManager.AppSettings["ChildForeignKey"]]);
            _dataSet.Relations.Add(dataRelation);
        }

        private void InitializeForm()
        {
            _bindingParentTable.DataSource = _dataSet;
            _bindingParentTable.DataMember = ConfigurationManager.AppSettings["ParentTableName"];

            _bindingChildTable.DataSource = _bindingParentTable;
            _bindingChildTable.DataMember = ConfigurationManager.AppSettings["ForeignKey"];

            dataGridViewParentTable.DataSource = _bindingParentTable;
            dataGridViewChildTable.DataSource = _bindingChildTable;
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void buttonUpdateDatabase_Click(object sender, EventArgs e)
        {
            _dataAdapterChildTable.Update(_dataSet, ConfigurationManager.AppSettings["ChildTableName"]!);
            _dataAdapterParentTable.Update(_dataSet, ConfigurationManager.AppSettings["ParentTableName"]!);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            InitializeDatabase();
            InitializeForm();
        }
    }
}