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
        private string _lastInstructorCNPSelected = String.Empty;

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
            adapter.SelectCommand = new SqlCommand($"SELECT * FROM {instructorsTableName}", _sqlConnection);
            adapter.Fill(_dataSet, "Instructors");
            instructorsDataGridView1.Refresh();
            _sqlConnection.Close();
        }

        private void displayVehiclesByInstructorId(string instructorCNP)
        {
            _sqlConnection.Open();
            string vehiclesTableName = "Vehicles";
            SqlDataAdapter adapter = new();
            // MessageBox.Show(instructorCNP);
            adapter.SelectCommand = new SqlCommand($"SELECT * FROM {vehiclesTableName} where InstructorCNP = {instructorCNP}", _sqlConnection);
            _dataSet.Tables["Vehicles"]?.Clear();
            adapter.Fill(_dataSet, "Vehicles");
            _lastInstructorCNPSelected = instructorCNP;
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
                    displayVehiclesByInstructorId(cellValue!);
                }
            }
            catch (ArgumentOutOfRangeException)
            {
                MessageBox.Show("Casuta invalida", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }

        private void addCarButton_Click(object sender, EventArgs e)
        {
            if (String.IsNullOrWhiteSpace(instructorCNPTextBox.Text) ||
                String.IsNullOrWhiteSpace(carChasisTextBox.Text) ||
                String.IsNullOrWhiteSpace(carPlateTextBox.Text))
            {
                //MessageBox.Show("Invalid text fields!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                displayMessageBoxError("Invalid text fields!");
                return;
            }

            try
            {
                _sqlConnection.Open();
                SqlCommand cmd = new SqlCommand($"Insert into Vehicles(InstructorCNP,CarChasis,CarPlate)" +
                    $" values(" +
                    $"{instructorCNPTextBox.Text}," +
                    $"{carChasisTextBox.Text}," +
                    $"'{carPlateTextBox.Text}')", _sqlConnection);

                cmd.ExecuteNonQuery();
                cmd.Dispose();
            }
            catch (Exception exception)
            {
                displayMessageBoxError(exception.Message);
            }
            finally
            {
                _sqlConnection.Close();
                displayVehiclesByInstructorId(_lastInstructorCNPSelected);
            }
        }

        private void displayMessageBoxError(string text)
        {
            MessageBox.Show(text, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            if (String.IsNullOrWhiteSpace(carChasisUpdateTextField.Text) ||
               String.IsNullOrWhiteSpace(carIdUpdateTextField.Text) ||
               String.IsNullOrWhiteSpace(carPlateUpdateTextField.Text) ||
               String.IsNullOrWhiteSpace(instructorCNPUpdateTextField.Text))
            {
                //MessageBox.Show("Invalid text fields!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                displayMessageBoxError("Invalid text fields!");
                return;
            }

            try
            {
                _sqlConnection.Open();
                SqlCommand cmd = new SqlCommand($"UPDATE Vehicles SET " +
                    $"InstructorCNP = {instructorCNPUpdateTextField.Text}," +
                    $"CarChasis = {carChasisUpdateTextField.Text}," +
                    $"CarPlate = '{carPlateUpdateTextField.Text}' " +
                    $"where Id = {carIdUpdateTextField.Text}", _sqlConnection);

                cmd.ExecuteNonQuery();
                cmd.Dispose();
            }
            catch (Exception exception)
            {
                displayMessageBoxError(exception.Message);
            }
            finally
            {
                _sqlConnection.Close();
                displayVehiclesByInstructorId(_lastInstructorCNPSelected);
            }
        }
    }
}