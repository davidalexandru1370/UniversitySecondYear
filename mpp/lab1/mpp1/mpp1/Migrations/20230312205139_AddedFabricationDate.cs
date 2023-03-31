using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace mpp1.Migrations
{
    /// <inheritdoc />
    public partial class AddedFabricationDate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<DateTime>(
                name: "FabricationDate",
                table: "Vehicle",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "FabricationDate",
                table: "Vehicle");
        }
    }
}
