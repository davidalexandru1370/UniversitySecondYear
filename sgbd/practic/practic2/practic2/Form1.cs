using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace practic2
{
    public partial class Form1 : Form
    {

        private string _connectionString = @"Data Source=localhost;Initial Catalog=practic3;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False";
        private SqlConnection _sqlConnection;
        private SqlDataAdapter _sqlDataAdapterParent, _sqlDataAdapterChild;
        private BindingSource _bindingSourceParentTable, _bindingSourceChildTable;
        private DataSet _dataSet;
        private SqlCommandBuilder _sqlCommandBuilder;
        private string parentTableName = "TeamsType";
        private string childTableName = "Teams";
        private string foreignKeyName = "FK_Teams_TeamsType";


        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {


            _sqlConnection = new SqlConnection(_connectionString);
            _sqlDataAdapterParent = new SqlDataAdapter($"SELECT * FROM {parentTableName}", _sqlConnection);
            _sqlDataAdapterChild = new SqlDataAdapter($"SELECT * FROM {childTableName}", _sqlConnection);
            _sqlDataAdapterChild.MissingSchemaAction = MissingSchemaAction.AddWithKey;

            _dataSet = new DataSet();
            _sqlDataAdapterParent.Fill(_dataSet, parentTableName);
            _sqlDataAdapterChild.Fill(_dataSet, childTableName);

            new SqlCommandBuilder(_sqlDataAdapterChild);
            new SqlCommandBuilder(_sqlDataAdapterParent);


            DataRelation dataRelation = new DataRelation(foreignKeyName,
                _dataSet.Tables[parentTableName].Columns["id"],
                _dataSet.Tables[childTableName].Columns["teamsType"]);

            _dataSet.Relations.Add(dataRelation);

            _bindingSourceParentTable = new BindingSource();
            _bindingSourceParentTable.DataSource = _dataSet;
            _bindingSourceParentTable.DataMember = parentTableName;

            _bindingSourceChildTable = new BindingSource();
            _bindingSourceChildTable.DataSource = _bindingSourceParentTable;
            _bindingSourceChildTable.DataMember = foreignKeyName;

            dgvTypes.DataSource = _bindingSourceParentTable;
            dgvTeams.DataSource = _bindingSourceChildTable;
        }
        private void saveButton_Click(object sender, EventArgs e)
        {
            _sqlDataAdapterParent.Update(_dataSet, parentTableName);
            _sqlDataAdapterChild.Update(_dataSet, childTableName);

            _dataSet.Tables[childTableName].Clear();
            _dataSet.Tables[parentTableName].Clear();
            _sqlDataAdapterParent.Fill(_dataSet, parentTableName);
            _sqlDataAdapterChild.Fill(_dataSet, childTableName);

        }
    }
}
