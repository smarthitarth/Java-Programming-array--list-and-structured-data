is_hamlet_speaking = false
File.open("hamlet.txt", "r") do |file|
  file.readlines.each do |line|

    if is_hamlet_speaking && (line.match(/^  [A-Z]/) || line.strip.empty?)
      is_hamlet_speaking = false
    end

    is_hamlet_speaking = true if line.match("Ham\.")

    puts line if is_hamlet_speaking
  end
end
