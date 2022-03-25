<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
		<title>Anime Data Visualization</title>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
        </script>
    </head>
    <body>
    <form action="index.php" method="POST">
            <select class="select" name="table">
                <option value="actors">Actors age distribution</option>
                <option value="studios">Amount of studios per location</option>
                <option value="animes">Anime Ratings</option>
                <option value="featured">Amount of anime actor has been in</option>
            </select><br>
            <select class="select" name="type">
                <option value="json">json</option>
                <option value="xml">xml</option>
            </select><br>
            <!-- <input class="select" type="text" name="textInput"><br> -->
            <input class="select" type="submit" value="Submit" name="SubmitForm">
        </form>
        <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
    </body>
</html>

<?php
function callAPI($method, $url, $data, $type)
{
    $curl = curl_init();
    switch ($method){
       case "POST":
          curl_setopt($curl, CURLOPT_POST, 1);
          if ($data)
             curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
          break;
       case "PUT":
          curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "PUT");
          if ($data)
             curl_setopt($curl, CURLOPT_POSTFIELDS, $data);			 					
          break;
       default:
          if ($data)
             $url = sprintf("%s?%s", $url, http_build_query($data));
    }
    // OPTIONS:
    curl_setopt($curl, CURLOPT_URL, $url);
	if($type == "xml")
	{
		curl_setopt($curl, CURLOPT_HTTPHEADER, array(
			'Accept: application/xml',
			'Content-Type: application/xml',
		));
	}
	else
	{
		curl_setopt($curl, CURLOPT_HTTPHEADER, array(
			'Accept: application/json',
			'Content-Type: application/json',
		));
	}

    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
    // EXECUTE:
    $result = curl_exec($curl);
    if(!$result){die("Connection Failure");}
    curl_close($curl);
    return $result;
 }
 
 
 function xmlToArray($xmlstring)
 {
	$xml = simplexml_load_string($xmlstring, "SimpleXMLElement", LIBXML_NOCDATA);
	$json = json_encode($xml);
	$array = json_decode($json);
	return $array;
 }
 
 
if ($_SERVER['REQUEST_METHOD'] === 'POST') 
{
	if (isset($_POST['SubmitForm'])) 
	{
		if($_POST['type'] == "json")
		{
			$data = callAPI("GET", "http://localhost:8080/" . $_POST['table'], false, "json");
			$decoded = json_decode($data);
			//var_dump($decoded);
		}
		else if($_POST['type'] == "xml")
		{
			$data = callAPI("GET", "http://localhost:8080/" . $_POST['table'], false, "xml");
			$decoded1 = xmlToArray($data);
			switch($_POST['table'])
			{
				case 'actors':
					$decoded = $decoded1->actor;
					break;
				case 'animes':
					$decoded = $decoded1->anime;
					break;
				case 'studios':
					$decoded = $decoded1->studio;
					break;
				case 'featured':
					$decoded = $decoded1->featuredIn;
					break;
			}
		}

		switch ($_POST['table']) {
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ACTORS
			case 'actors': 
				$oldest = 0;
				$youngest = 1000;
				for($i = 0; $i < count($decoded); $i++)
				{
					if($decoded[$i]->age > $oldest)
					{
						$oldest = $decoded[$i]->age;
					}
					
					if($decoded[$i]->age < $youngest)
					{
						$youngest = $decoded[$i]->age;
					}
				}
				
				$ageArray = [];
				for($i = 0; $i <= $oldest; $i++)
				{
					$ageCounter = 0;
					for($j = 0; $j < count($decoded); $j++)
					{
						if($i == $decoded[$j]->age)
						{
							$ageCounter++;
						}
					}
					array_push($ageArray, $ageCounter);
				}

				?>
				<script>
					var xValues = 
					[<?php 
						for($i = 0; $i <= $oldest; $i++)
						{
							echo('"' . $i .'", ');
						}
					?>];
					
					var yValues = 
					[<?php 
						for($i = 0; $i < count($ageArray); $i++)
						{
							echo('"' . $ageArray[$i] .'", ');
						}
					?>];
					var barColors = "red";

					new Chart("myChart", {
					type: "bar",
					data: {
						labels: xValues,
						datasets: [{
							backgroundColor: barColors,
							data: yValues
						}]
					},
					options: {
						legend: {display: false},
						title: {
						display: true,
						text: "Actors age Distribution"
						},
						scales: {
							yAxes: [{
								scaleLabel: {
									display: true,
									labelString: 'Amount'
								}
							}],
							xAxes: [{
								scaleLabel: {
									display: true,
									labelString: 'Age'
								}
							}]
						}
					}
					});
				</script>
				<?php
				break;
            ///////////////////////////////////////////////////////////////////////////////////////////////////// STUDIOS
			case 'studios':
				$locationsFilter = [];
				//var_dump($decoded);
				for($i = 0; $i < count($decoded); $i++)
				{
					array_push($locationsFilter, $decoded[$i]->headquarters);
				}
				$locations = array_values(array_unique($locationsFilter));
				
				$locationsAmount = [];
				for($k = 0; $k < count($locations); $k++)
				{
					$counter = 0;
					for($i = 0; $i < count($decoded); $i++)
					{
						if(strcmp($decoded[$i]->headquarters, $locations[$k]) === 0)
						{
							$counter++;
						}
					}
					array_push($locationsAmount, $counter);
				}
			
				?>
				<script>
					var xValues = 
					[<?php 
						for($i = 0; $i < count($locations); $i++)
						{
							echo('"' . $locations[$i] .'", ');
						}
					?>];
					
					var yValues = 
					[<?php 
						for($i = 0; $i < count($locationsAmount); $i++)
						{
							echo('"' . $locationsAmount[$i] .'", ');
						}
					?>];
					var barColors = "blue";

					new Chart("myChart", {
					type: "bar",
					data: {
						labels: xValues,
						datasets: [{
							backgroundColor: barColors,
							data: yValues
						}]
					},
					options: {
						legend: {display: false},
						title: {
						display: true,
						text: "Amount of studios per location"
						},
						scales: {
							yAxes: [{
								ticks: {
									beginAtZero: true,
									stepSize: 1
								},
								scaleLabel: {
									display: true,
									labelString: 'Amount'
								}
							}],
							xAxes: [{
								scaleLabel: {
									display: true,
									labelString: 'Location'
								}
							}]
						}
					}
					});
				</script>
				<?php
				break;
            ///////////////////////////////////////////////////////////////////////////////////////////////////// ANIMES
			case 'animes':
				$names = [];
				$scores = [];
				
				for($counter = 1; $counter < count($decoded); $counter++)
				{
					for($i = 0; $i < count($decoded); $i++)
					{
						if($decoded[$i]->critic_score == $counter)
						{
							array_push($names, $decoded[$i]->name);
							array_push($scores, $decoded[$i]->critic_score);
						}
					}
				}
				$names = array_reverse($names);
				$scores = array_reverse($scores);
			
			
				?>
				<script>
					var xValues = 
					[<?php 
						for($i = 0; $i < count($names); $i++)
						{
							echo('"' . $names[$i] .'", ');
						}
					?>];
					
					var yValues = 
					[<?php 
						for($i = 0; $i < count($scores); $i++)
						{
							echo('"' . $scores[$i] .'", ');
						}
					?>];
					var barColors = "green";

					new Chart("myChart", {
					type: "bar",
					data: {
						labels: xValues,
						datasets: [{
							backgroundColor: barColors,
							data: yValues
						}]
					},
					options: {
						legend: {display: false},
						title: {
						display: true,
						text: "Anime Ratings"
						},
						scales: {
							yAxes: [{
								ticks: {
									beginAtZero: true,
									stepSize: 1
								},
								scaleLabel: {
									display: true,
									labelString: 'Critic Score'
								}
							}],
							xAxes: [{
								scaleLabel: {
									display: true,
									labelString: 'Anime Name'
								}
							}]
						}
					}
					});
				</script>
				<?php
				break;
			/////////////////////////////////////////////////////////////////////////////////////////// FEATURED IN
			case 'featured':
				$actorIds = [];

				//var_dump($decoded);
				for($i = 0; $i < count($decoded); $i++)
				{
					array_push($actorIds, $decoded[$i]->actor->id);

				}
				$idFilter = array_values(array_unique($actorIds));
				
				$actorNames = [];
				$amount = [];
				for($k = 0; $k < count($idFilter); $k++)
				{
					$counter = 0;
					for($i = 0; $i < count($decoded); $i++)
					{
						if(strcmp($decoded[$i]->actor->id, $idFilter[$k]) === 0)
						{
							$counter++;
							$name = $decoded[$i]->actor->name;
						}
					}
					array_push($amount, $counter);
					array_push($actorNames, $name);
				}
			
			
				?>
				<script>
					var xValues = 
					[<?php 
						for($i = 0; $i < count($actorNames); $i++)
						{
							echo('"' . $actorNames[$i] .'", ');
						}
					?>];
					
					var yValues = 
					[<?php 
						for($i = 0; $i < count($amount); $i++)
						{
							echo('"' . $amount[$i] .'", ');
						}
					?>];
					var barColors = "pink";

					new Chart("myChart", {
					type: "bar",
					data: {
						labels: xValues,
						datasets: [{
							backgroundColor: barColors,
							data: yValues
						}]
					},
					options: {
						legend: {display: false},
						title: {
						display: true,
						text: "Amount of anime actor has been in"
						},
						scales: {
							yAxes: [{
								ticks: {
									beginAtZero: true,
									stepSize: 1
								},
								scaleLabel: {
									display: true,
									labelString: 'Amount'
								}
							}],
							xAxes: [{
								scaleLabel: {
									display: true,
									labelString: 'Actor'
								}
							}]
						}
					}
					});
				</script>
				<?php
				break;
			}
		}
	}

?>
