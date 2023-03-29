using System.Data;
using System.Data.SqlClient;

namespace lab1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            instructorsDataGridView1.AutoGenerateColumns = true;
            
        }
        private static string _connectionString =
            "Data Source=localhost;" +
            "Initial Catalog=DrivingExams21;" +
            "Integrated Security=True;" +
            "Connect Timeout=30;" +
            "Encrypt=False;" +
            "TrustServerCertificate=False;" +
            "ApplicationIntent=ReadWrite;" +
            "MultiSubnetFailover=False";
        private DataSet _dataSet = new();
        private SqlConnection _sqlConnection = new(_connectionString);
        private void Form1_Load(object sender, EventArgs e)
        {
            DisplayAllInstructors();
            instructorsDataGridView1.DataSource = _dataSet.Tables["Instructors"];
        }

        private void DisplayAllInstructors()
        {
            _sqlConnection.Open();
            string instructorsTableName = "Instructors";
            SqlDataAdapter adapter = new();
            adapter.SelectCommand = new SqlCommand($"SELECT * FROM {instructorsTableName}",_sqlConnection);
            adapter.Fill(_dataSet,"Instructors");
            instructorsDataGridView1.Refresh();
            _sqlConnection.Close();
        }

        private void DisplayVehiclesByInstructorId(string instructorCNP)
        {
            _sqlConnection.Open();
            string vehiclesTableName = "Vehicles";
            SqlDataAdapter adapter = new();
            // MessageBox.Show(instructorCNP);
            adapter.SelectCommand = new SqlCommand($"SELECT * FROM {vehiclesTableName} where InstructorCNP = {instructorCNP}", _sqlConnection);
            _dataSet.Tables["Vehicles"]?.Clear();
            adapter.Fill(_dataSet, "Vehicles");
            
            vehiclesDataGridView.DataSource = _dataSet.Tables["Vehicles"];
            vehiclesDataGridView.Refresh();
            _sqlConnection.Close();
        }

        private void vehiclesDataGridView_CellClick(object sender, DataGridViewCellEventArgs e)
        {
           
        }

        private void instructorsDataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            var cell = sender as DataGridView;
            try
            {
                if (e.ColumnIndex == 0)
                {
                    var cellValue = instructorsDataGridView1.Rows[e.RowIndex].Cells[e.ColumnIndex].Value.ToString();
                    //MessageBox.Show(cellValue);
                    DisplayVehiclesByInstructorId(cellValue);
                }
            }
            catch (ArgumentOutOfRangeException)
            {
                MessageBox.Show("Casuta invalida", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
           
        }
    }
}