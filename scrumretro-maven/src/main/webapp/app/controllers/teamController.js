app.controller('teamController', function ($scope, teamService) {

    function init() {
        $scope.teamMembers = teamService.getTeamMembers();
    }

    $scope.insertCustomer = function () {
        var firstName = $scope.newTeamMember.firstName;
        var lastName = $scope.newTeamMember.lastName;
        var city = $scope.newTeamMember.email;
        customersService.insertTeamMember(email, firstName, lastName);
        $scope.newCustomer.firstName = '';
        $scope.newCustomer.lastName = '';
        $scope.newCustomer.email = '';
    };

    $scope.deleteTeamMember = function (id) {
        teamService.deleteTeamMember(id);
    };
});

