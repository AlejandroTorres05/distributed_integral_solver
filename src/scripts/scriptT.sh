number=("1000" "10000" "100000" "1000000" "10000000" "100000000" "1000000000")
functionS="x^2"
lRange="0"
uRange="1"

cd "../client/build/libs"

for numbers in "${number[@]}"
do
    java -jar client.jar test 1 $numbers $functionS $lRange $uRange
done