app.service('teamService', function () {

    this.getTeamMembers = function () {
        return teamMembers;
    };

    this.insertTeamMember = function (id, firstName, lastName) {
        teamMembers.push({
            id: id,
            firstName: firstName,
            lastName: lastName,
        });
    };

    this.getInactiveTeamMember = function () {
        for (var i = 0; i < teamMembers.length; i++) {
            if (teamMembers[i].active == false) {
                return teamMembers[i];
            }
        }
        return null;
    };
    
    this.deleteTeamMember(id){
    	console.long(id);
    };
    
    var teamMembers = [
        {
            id: 'durga@durga.com', firstName: 'Durga', lastName: 'Manchikanti', active : false
        },
        {
            id: 'ragil@ragil.com', firstName: 'Ragil', lastName: 'Chandran', active : true
        },
        {
            id: 'kalai@sanju.com', firstName: 'Kalai', lastName: 'Sanju', active : true
        }
    ];

});