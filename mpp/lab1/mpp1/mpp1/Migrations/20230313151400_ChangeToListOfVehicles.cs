using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace mpp1.Migrations
{
    /// <inheritdoc />
    public partial class ChangeToListOfVehicles : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Incidents_Vehicle_VehicleId",
                table: "Incidents");

            migrationBuilder.RenameColumn(
                name: "VehicleId",
                table: "Incidents",
                newName: "VehicleId1");

            migrationBuilder.RenameIndex(
                name: "IX_Incidents_VehicleId",
                table: "Incidents",
                newName: "IX_Incidents_VehicleId1");

            migrationBuilder.AddColumn<Guid>(
                name: "Vehicle",
                table: "Vehicle",
                type: "uniqueidentifier",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Vehicle_Vehicle",
                table: "Vehicle",
                column: "Vehicle");

            migrationBuilder.AddForeignKey(
                name: "FK_Incidents_Vehicle_VehicleId1",
                table: "Incidents",
                column: "VehicleId1",
                principalTable: "Vehicle",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Vehicle_Incidents_Vehicle",
                table: "Vehicle",
                column: "Vehicle",
                principalTable: "Incidents",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Incidents_Vehicle_VehicleId1",
                table: "Incidents");

            migrationBuilder.DropForeignKey(
                name: "FK_Vehicle_Incidents_Vehicle",
                table: "Vehicle");

            migrationBuilder.DropIndex(
                name: "IX_Vehicle_Vehicle",
                table: "Vehicle");

            migrationBuilder.DropColumn(
                name: "Vehicle",
                table: "Vehicle");

            migrationBuilder.RenameColumn(
                name: "VehicleId1",
                table: "Incidents",
                newName: "VehicleId");

            migrationBuilder.RenameIndex(
                name: "IX_Incidents_VehicleId1",
                table: "Incidents",
                newName: "IX_Incidents_VehicleId");

            migrationBuilder.AddForeignKey(
                name: "FK_Incidents_Vehicle_VehicleId",
                table: "Incidents",
                column: "VehicleId",
                principalTable: "Vehicle",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
