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

namespace practic1
{
    public partial class Form1 : Form
    {
        private string _connectionString = @"Data Source=localhost;Initial Catalog=practic1;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False";
        private SqlConnection _sqlConnection;
        private SqlDataAdapter _sqlDataAdapterUsers, _sqlDataAdapterPosts;
        private BindingSource _bindingSourceUsers, _bindingSourcePosts;
        private DataSet _dataSet;
        private SqlCommandBuilder _sqlCommandBuilder;



        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            _sqlConnection = new SqlConnection(_connectionString);
            _sqlDataAdapterUsers = new SqlDataAdapter("SELECT * FROM Users", _sqlConnection);
            _sqlDataAdapterPosts = new SqlDataAdapter("SELECT * FROM Posts", _sqlConnection);
            _sqlDataAdapterPosts.MissingSchemaAction = MissingSchemaAction.AddWithKey;

            _dataSet = new DataSet();
            _sqlDataAdapterUsers.Fill(_dataSet, "Users");
            _sqlDataAdapterPosts.Fill(_dataSet, "Posts");

            new SqlCommandBuilder(_sqlDataAdapterPosts);

            DataRelation dataRelation = new DataRelation("FK_Posts_User",
                _dataSet.Tables["Users"].Columns["id"],
                _dataSet.Tables["Posts"].Columns["userId"]);

            _dataSet.Relations.Add(dataRelation);

            _bindingSourceUsers = new BindingSource();
            _bindingSourceUsers.DataSource = _dataSet;
            _bindingSourceUsers.DataMember = "Users";

            _bindingSourcePosts = new BindingSource();
            _bindingSourcePosts.DataSource = _bindingSourceUsers;
            _bindingSourcePosts.DataMember = "FK_Posts_User";

            dgvUsers.DataSource = _bindingSourceUsers;
            dgvPosts.DataSource = _bindingSourcePosts;
        }
        private void saveButton_Click(object sender, EventArgs e)
        {
            _sqlDataAdapterPosts.Update(_dataSet, "Posts");
            _sqlDataAdapterUsers.Update(_dataSet, "Users");

            _dataSet.Tables["Posts"].Clear();
            _dataSet.Tables["Users"].Clear();
            _sqlDataAdapterUsers.Fill(_dataSet, "Users");
            _sqlDataAdapterPosts.Fill(_dataSet, "Posts");

        }
    }
}
