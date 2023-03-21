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
            vehiclesDataGridView.DataSource = _dataSet.Tables["Vehicles"];
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
            adapter.SelectCommand = new SqlCommand($"SELECT * FROM {vehiclesTableName} where InstructorCNP={instructorCNP}");
            adapter.Fill(_dataSet, "Vehicles");
            vehiclesDataGridView.Refresh();
            _sqlConnection.Close();
        }

        private void instructorsDataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            var cell = sender as DataGridView;
            if (e.RowIndex == 0)
            {
                DisplayVehiclesByInstructorId(cell[e.RowIndex, e.ColumnIndex].ToString());
            }
        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}